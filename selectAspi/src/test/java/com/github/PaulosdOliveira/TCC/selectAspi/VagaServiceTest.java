package com.github.PaulosdOliveira.TCC.selectAspi;

import com.github.PaulosdOliveira.TCC.selectAspi.application.vaga.VagaEmpregoService;
import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.MensagemRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.VagaEmpregoRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Sexo;
import com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem.Mensagem;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.CadastroVagaDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.enums.Modelo;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.enums.Nivel;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.enums.TipoContrato;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class VagaServiceTest {

    @Autowired
    private VagaEmpregoService service;

    @Autowired
    private MensagemRepository mensagemRepository;


    @Test
    void cadastrarVagaTest() {
        CadastroVagaDTO dados = new CadastroVagaDTO();
        dados.setCep("01021-000");
        dados.setTitulo("Lavador de carro");
        dados.setDescricao_vaga("Estamos em busca de um(a) Atendente de Loja para integrar nossa equipe e oferecer um atendimento de qualidade aos clientes. O(a) profissional será responsável por auxiliar os clientes durante todo o processo de compra, garantir a organização do ambiente e contribuir para uma experiência positiva na loja.#");
       dados.setPrincipais_atividades("Recepcionar os clientes com cordialidade e atenção;\n" +
                                      "\n" +
                                      "Auxiliar na escolha de produtos e esclarecer dúvidas;\n" +
                                      "\n" +
                                      "Realizar atendimento no caixa e processar pagamentos;\n" +
                                      "\n" +
                                      "Organizar e repor mercadorias nas prateleiras;\n" +
                                      "\n" +
                                      "Zelar pela limpeza e organização da loja;\n" +
                                      "\n" +
                                      "Apoiar nas rotinas de estoque e inventário#");
       dados.setRequisitos("Ensino médio completo;\n" +
                           "\n" +
                           "Boa comunicação e simpatia no atendimento;\n" +
                           "\n" +
                           "Pontualidade e comprometimento;\n" +
                           "\n" +
                           "Disponibilidade para trabalhar em horários variados, inclusive finais de semana.#");
       dados.setDiferenciais("Experiência anterior com atendimento ao público ou vendas;\n" +
                             "\n" +
                             "Conhecimento básico em operação de caixa;\n" +
                             "\n" +
                             "Facilidade para lidar com metas e trabalho em equipe.#");

       dados.setLocal_de_trabalho("Loja física localizada em [Nome do Bairro ou Cidade]\n" +
                           "(Adaptar conforme a sua base de dados)#");
       dados.setHorario("Escala de trabalho 6x1 — das 13h às 21h, com uma folga semanal e um domingo por mês.#");
        dados.setNivel(Nivel.INDEFINIDO);
        dados.setModelo(Modelo.PRESENCIAL);
        dados.setDiasEmAberto(3L);
        dados.setExclusivoParaPcd(false);
        dados.setExclusivoParaSexo(Sexo.TODOS);
        dados.setTipoContrato(TipoContrato.CLT);
        dados.setSalario(1200.45F);
        service.cadastrarVaga(dados);

    }


}
