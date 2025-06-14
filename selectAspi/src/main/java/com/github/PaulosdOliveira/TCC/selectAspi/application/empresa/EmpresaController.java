package com.github.PaulosdOliveira.TCC.selectAspi.application.empresa;

import com.github.PaulosdOliveira.TCC.selectAspi.application.qualificacao.QualificacaoService;
import com.github.PaulosdOliveira.TCC.selectAspi.model.empresa.DadosLoginEmpresaDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.empresa.CadastroEmpresaDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.Qualificacao;
import com.github.PaulosdOliveira.TCC.selectAspi.application.UtilsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;

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

    @PostMapping("/login")
    public String getAccessToken(@RequestBody @Valid DadosLoginEmpresaDTO dadosLogin) {
        return service.getAccessToken(dadosLogin);
    }

    @GetMapping("/foto/{id}")
    public ResponseEntity<byte[]> renderizarFoto(@PathVariable Long id) {
        byte[] foto = service.buscarFotoEmpresa(id);
        return utils.renderizarFoto(foto);
    }

    @GetMapping("/capa/{id}")
    public ResponseEntity<byte[]> renderizarCapa(@PathVariable Long id) {
        byte[] foto = service.buscarCapaEmpresa(id);
        return utils.renderizarFoto(foto);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/qualificacao")
    public void cadastrarQualificacao(@RequestBody @Valid Qualificacao qualificacao) {
        qualificacaoService.cadastrarQualificacao(qualificacao);
    }


}
