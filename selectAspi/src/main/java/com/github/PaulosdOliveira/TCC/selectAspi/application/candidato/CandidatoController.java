package com.github.PaulosdOliveira.TCC.selectAspi.application.candidato;

import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.ConsultaQualificacaoUsuario;
import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.QualificacaoUsuarioDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.DadosLoginCandidatoDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.CadastroCandidatoDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.ConsultaCandidatoDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.application.UtilsService;
import com.github.PaulosdOliveira.TCC.selectAspi.jwt.Token;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("candidato")
public class CandidatoController {

    private final CandidatoService service;
    private final UtilsService utils;

    @PostMapping
    public ResponseEntity<String> cadastrarCandidato(@RequestBody @Valid CadastroCandidatoDTO dadosCadastrais) throws Exception {
        service.cadastrarUsuario(dadosCadastrais);
        return new ResponseEntity<>("Cadastro realizado com sucesso", HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/foto")
    public void salvarFotoCandidato(@RequestParam MultipartFile foto) throws IOException {
        service.salvarFotoCandidato(foto.getBytes());
    }

    @GetMapping("/foto/{idCandidato}")
    public ResponseEntity<byte[]> buscarFotoCandidato(@PathVariable Long idCandidato) {
        byte[] foto = service.buscarFotoCandidato(idCandidato);
        return utils.renderizarFoto(foto);
    }

    @PostMapping(value = "/curriculo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void salvarCurriculoCandidato(@RequestParam MultipartFile curriculoPdf) throws IOException {
        service.salvarCurriculo(curriculoPdf.getBytes());
    }


    @GetMapping("/curriculo/{idCandidato}")
    public ResponseEntity<byte[]> buscarCurriculoCandidato(@PathVariable Long idCandidato) {
        byte[] curriculo = service.buscarCurriculoCandidato(idCandidato);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        return new ResponseEntity<>(curriculo, headers, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public Token login(@RequestBody @Valid DadosLoginCandidatoDTO dadosLogin) {
        String token = service.getCandidatoAccessToken(dadosLogin);
        System.out.println(token + ":::::::::::::");
        return new Token(token);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/qualificacao-candidato")
    public void cadastrarQualificacao(@RequestBody  List< @Valid QualificacaoUsuarioDTO> qualificacoes) {
        service.cadastarQualificacaoUsuario(qualificacoes);
    }

    @PostMapping("/qualificacao-candidato/consulta")
    public List<ConsultaCandidatoDTO> findByQ(
            @RequestParam(name = "estado", required = false) String estado,
            @RequestParam(name = "cidade", required = false) String cidade,
            @RequestBody List<@Valid ConsultaQualificacaoUsuario> qualificacoes) {
        return service.findByQualificacao(qualificacoes, estado, cidade).stream().map(ConsultaCandidatoDTO::new).toList();
    }


}

