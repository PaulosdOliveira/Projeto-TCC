package com.github.PaulosdOliveira.TCC.selectAspi.config;

import com.github.PaulosdOliveira.TCC.selectAspi.application.candidato.CandidatoService;
import com.github.PaulosdOliveira.TCC.selectAspi.application.empresa.EmpresaService;
import com.github.PaulosdOliveira.TCC.selectAspi.jwt.JwtFilter;
import com.github.PaulosdOliveira.TCC.selectAspi.jwt.JwtService;
import com.github.PaulosdOliveira.TCC.selectAspi.model.AuthSocket;
import com.github.PaulosdOliveira.TCC.selectAspi.model.token.DadosToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.List;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {


    @Autowired
    private JwtService jwtService;

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private CandidatoService candidatoService;


    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/mensagem")
                .setHeartbeatValue(new long[]{8000, 8000})
                .setTaskScheduler(taskScheduler());
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/conect")
                .setAllowedOriginPatterns("*");
    }

    @Bean
    public ThreadPoolTaskScheduler taskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

    // RECEBENDO TOKEN DE LOGIN VIA WS
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                assert accessor != null;
                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
                    List<String> authorization = accessor.getNativeHeader("Authorization");
                    if (authorization != null && !authorization.isEmpty()) {
                        String token = authorization.getFirst().substring(7);

                        // COLETANDO PERFIL E EMAIL DO USUÁRIO
                        DadosToken dadosToken = jwtService.getDadosToken(token);
                        AuthSocket authSocket;

                        // CRIANDO USER DETAILS DE CANDIDATO A PARTIR DO EMAIL RECUPERADO NO TOKEN
                        if (dadosToken.getPerfil().equals("candidato")) {
                            authSocket = candidatoService.getCandidatoUserDetails(dadosToken.getEmail());
                        } else {
                            // CRIANDO USER DETAILS DE EMPRESA A PARTIR DO EMAIL RECUPERADO NO TOKEN
                            authSocket = empresaService.getEmpresaUserDetails(dadosToken.getEmail());
                        }
                        Authentication auth = new UsernamePasswordAuthenticationToken(authSocket.getUserDetails(), authSocket.getIdUsuario(),
                                authSocket.getUserDetails().getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(auth);

                        // LOGANDO O USUÁRIO NA SESSÃO DO WS
                        accessor.setUser(auth);


                    } else {
                        // HEADER VAZIO
                        System.out.println("Nenhum token encontrado no header Authorization");
                        return null;
                    }
                }
                return message;
            }
        });
    }
}
