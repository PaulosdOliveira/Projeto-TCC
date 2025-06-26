package com.github.PaulosdOliveira.TCC.selectAspi.application.empresa;

import com.github.PaulosdOliveira.TCC.selectAspi.application.qualificacao.QualificacaoService;
import com.github.PaulosdOliveira.TCC.selectAspi.jwt.Token;
import com.github.PaulosdOliveira.TCC.selectAspi.model.empresa.DadosLoginEmpresaDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.empresa.CadastroEmpresaDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.Qualificacao;
import com.github.PaulosdOliveira.TCC.selectAspi.application.UtilsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("empresa")
public class EmpresaController {

    private final EmpresaService service;
    private final QualificacaoService qualificacaoService;
    private final UtilsService utils;


    @PostMapping
    public void cadastrarEmpresa(@RequestBody @Valid CadastroEmpresaDTO dadosCadastrais) {
        service.cadastrarEmpresa(dadosCadastrais);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public Token getAccessToken(@RequestBody @Valid DadosLoginEmpresaDTO dadosLogin) {

        return new Token(service.getAccessToken(dadosLogin));
    }

    @PostMapping("/foto")
    public void salvarFoto(@RequestParam MultipartFile foto) throws IOException {
        System.out.println("OIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII");
        service.salvarFoto(foto.getBytes());
    }

    @GetMapping("/foto/{id}")
    public ResponseEntity<byte[]> renderizarFoto(@PathVariable UUID id) {
        byte[] foto = service.buscarFotoEmpresa(id);
        return utils.renderizarFoto(foto);
    }

    @GetMapping("/capa/{id}")
    public ResponseEntity<byte[]> renderizarCapa(@PathVariable UUID id) {
        byte[] foto = service.buscarCapaEmpresa(id);
        return utils.renderizarFoto(foto);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/qualificacao")
    public void cadastrarQualificacao(@RequestBody @Valid Qualificacao qualificacao) {
        qualificacaoService.cadastrarQualificacao(qualificacao);
    }


}
