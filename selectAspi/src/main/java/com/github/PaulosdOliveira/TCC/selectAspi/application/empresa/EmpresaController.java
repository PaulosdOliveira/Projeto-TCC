package com.github.PaulosdOliveira.TCC.selectAspi.application.empresa;

import com.github.PaulosdOliveira.TCC.selectAspi.application.UtilsService;
import com.github.PaulosdOliveira.TCC.selectAspi.model.empresa.CadastroEmpresaDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.empresa.DadosLoginEmpresaDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("empresa")
public class EmpresaController {


    private final EmpresaService service;
    private final UtilsService utils;


    @PostMapping
    public void cadastrarEmpresa(@RequestBody @Valid CadastroEmpresaDTO dadosCadastrais) {
        service.cadastrarEmpresa(dadosCadastrais);
    }

    @PostMapping("/login")
    public void getAccessToken(@RequestBody @Valid DadosLoginEmpresaDTO dadosLogin) {
        service.getAccessToken(dadosLogin);
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


}
