package com.github.PaulosdOliveira.TCC.selectAspi.application.candidato;

import com.github.PaulosdOliveira.TCC.selectAspi.application.UtilsService;
import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.CadastroCandidatoDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.DadosLoginCandidatoDTO;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("candidato")
public class CandidatoController {

    private final CandidatoService service;
    private final UtilsService utils;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void cadastrarCandidato(@RequestBody @Valid CadastroCandidatoDTO dadosCadastrais) throws Exception {
        service.cadastrarUsuario(dadosCadastrais);
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

    @PostMapping("/curriculo")
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
    public String login(@RequestBody @Valid DadosLoginCandidatoDTO dadosLogin) {
        return service.getCandidatoAccessToken(dadosLogin);
    }

}
