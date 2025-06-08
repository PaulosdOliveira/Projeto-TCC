package com.github.PaulosdOliveira.TCC.selectAspi.application.candidato;

import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.CadastroCandidatoDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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


    @GetMapping("/foto")
    public ResponseEntity<byte[]> buscarFotoCandidato() {
        byte[] foto = service.buscarFotoCandidato();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentLength(foto.length);
        return new ResponseEntity<>(foto, headers, HttpStatus.OK);
    }

    @PostMapping("/curriculo")
    @ResponseStatus(HttpStatus.OK)
    public void salvarCurriculoCandidato(@RequestParam MultipartFile curriculoPdf) throws IOException {
        service.salvarCurriculo(curriculoPdf.getBytes());
    }


    @GetMapping("/curriculo")
    public ResponseEntity<byte[]> buscarCurriculoCandidato() {
        byte[] curriculo = service.buscarCurriculoCandidato();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        return new ResponseEntity<>(curriculo, headers, HttpStatus.OK);
    }

}
