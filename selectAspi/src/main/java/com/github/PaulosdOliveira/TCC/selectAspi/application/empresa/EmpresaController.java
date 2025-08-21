package com.github.PaulosdOliveira.TCC.selectAspi.application.empresa;

import com.github.PaulosdOliveira.TCC.selectAspi.application.qualificacao.QualificacaoService;
import com.github.PaulosdOliveira.TCC.selectAspi.jwt.Token;
import com.github.PaulosdOliveira.TCC.selectAspi.model.empresa.DadosLoginEmpresaDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.empresa.CadastroEmpresaDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.empresa.PerfilEmpresa;
import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.Qualificacao;
import com.github.PaulosdOliveira.TCC.selectAspi.application.UtilsService;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('empresa')")
    @PostMapping("/foto")
    public void salvarFoto(@RequestParam MultipartFile foto) throws IOException {
        service.salvarFoto(foto.getBytes());
    }

    @PreAuthorize("hasRole('empresa')")
    @PostMapping("/capa")
    public void salvarCapa(@RequestParam MultipartFile foto) throws IOException {
        service.salvarCapa(foto.getBytes());
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


    @GetMapping("/{id}")
    public PerfilEmpresa carregarPerfil(@PathVariable UUID id) {
        return service.carregarPerfil(id);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/qualificacao")
    public void cadastrarQualificacao(@RequestBody @Valid Qualificacao qualificacao) {
        qualificacaoService.cadastrarQualificacao(qualificacao);
    }


}
