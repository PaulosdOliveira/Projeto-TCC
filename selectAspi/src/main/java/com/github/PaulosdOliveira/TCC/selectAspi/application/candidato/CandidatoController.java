package com.github.PaulosdOliveira.TCC.selectAspi.application.candidato;


import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.*;
import com.github.PaulosdOliveira.TCC.selectAspi.application.UtilsService;
import com.github.PaulosdOliveira.TCC.selectAspi.jwt.Token;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("candidato")
public class CandidatoController {

    private final CandidatoService service;
    private final UtilsService utils;

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cadastro realizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Cidade não confere com o estado"),
            @ApiResponse(responseCode = "409", description = "CPF já cadastradp"),
            @ApiResponse(responseCode = "409", description = "Email já cadastradp"),
            @ApiResponse(responseCode = "403", description = "A idade mínima para se cadastrar é de 16 anos"),
    }
    )
    @PostMapping
    public ResponseEntity<String> cadastrarCandidato(@RequestBody @Valid CadastroCandidatoDTO dadosCadastrais) throws Exception {
        service.cadastrarUsuario(dadosCadastrais);
        return new ResponseEntity<>("Cadastro realizado com sucesso", HttpStatus.CREATED);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Foto alterada com sucesso"),
            @ApiResponse(responseCode = "403", description = "Falha na autenticação")
    })
    @PreAuthorize("hasRole('candidato')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/foto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void salvarFotoCandidato(@RequestParam MultipartFile foto) throws IOException {
        service.salvarFotoCandidato(foto.getBytes());
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Nenhuma foto encontrada"),
    })
    @GetMapping("/foto/{idCandidato}")
    public ResponseEntity<?> buscarFotoCandidato(@PathVariable Long idCandidato) {
        byte[] foto = service.buscarFotoCandidato(idCandidato);
        if (foto != null) {
            return utils.renderizarFoto(foto);
        }
        return new ResponseEntity<String>("Nenhuma foto encotrada", HttpStatus.NOT_FOUND);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Currículo alterado com sucesso"),
            @ApiResponse(responseCode = "403", description = "Falha na autenticação")
    })
    @PreAuthorize("hasRole('candidato')")
    @PutMapping(value = "/curriculo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editarCurriculoCandidato(@RequestParam MultipartFile curriculoPdf) throws IOException {
        service.salvarCurriculo(curriculoPdf.getBytes());
    }

    @ApiResponse(responseCode = "200")
    @GetMapping("/curriculo/{idCandidato}")
    public ResponseEntity<byte[]> buscarCurriculoCandidato(@PathVariable Long idCandidato) {
        byte[] curriculo = service.buscarCurriculoCandidato(idCandidato);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        return new ResponseEntity<>(curriculo, headers, HttpStatus.OK);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login realizaod com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "404", description = "Login e/ou senha incorretos"),
    })
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public Token login(@RequestBody @Valid DadosLoginCandidatoDTO dadosLogin) {
        String token = service.getCandidatoAccessToken(dadosLogin);
        return new Token(token);
    }


    // BUSCA CANDIDATOS A PARTIR DE FILTROS (ESTADO, CIDADE, QUAIFICAÇÕES, FORMAÇÕES, ETC...)
    @ApiResponse(responseCode = "200")
    @PostMapping("/qualificacao-candidato/buscar-candidatos")
    public List<ConsultaCandidatoDTO> findByQualificacao(@RequestBody DadosConsultaCandidatoDTO dadosConsulta) {
        return service.findByQualificacao(dadosConsulta)
                .stream().map(candidato ->
                        new ConsultaCandidatoDTO(candidato.getId(), candidato.getNome(), candidato.getCidade().getNome(), candidato.getEstado().getSigla(),
                                candidato.getDataNascimento())).toList();
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
    })
    @GetMapping("/{id}")
    public PerfilCandidatoDTO carregarPerfil(@PathVariable Long id) {
        return service.carregarPerfil(id);
    }


    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
    })
    @GetMapping("/dadosSalvos")
    public EdicaoCandidatoDTO buscarDadosSalvos() {
        return service.buscarDadosSalvos();
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204")
    })
    @PutMapping
    public void editarPerfil(@RequestBody @Valid EdicaoCandidatoDTO novosDados) {
        service.editarDados(novosDados);
    }

}

