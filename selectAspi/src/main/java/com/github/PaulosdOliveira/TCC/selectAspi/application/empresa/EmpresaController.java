package com.github.PaulosdOliveira.TCC.selectAspi.application.empresa;

import com.github.PaulosdOliveira.TCC.selectAspi.application.qualificacao.QualificacaoService;
import com.github.PaulosdOliveira.TCC.selectAspi.jwt.Token;
import com.github.PaulosdOliveira.TCC.selectAspi.model.empresa.DadosLoginEmpresaDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.empresa.CadastroEmpresaDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.empresa.PerfilEmpresa;
import com.github.PaulosdOliveira.TCC.selectAspi.model.empresa.proposta.CadastroModeloDePropostaDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.empresa.proposta.ModeloDePropostaDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.Qualificacao;
import com.github.PaulosdOliveira.TCC.selectAspi.application.UtilsService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("empresa")
public class EmpresaController {

    private final EmpresaService service;
    private final UtilsService utils;
    private final ModeloDePropostaService propostaService;


    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cadastro realizado com sucesso"),
            @ApiResponse(responseCode = "209", description = "CNPJ já cadastrado"),
            @ApiResponse(responseCode = "209", description = "Email já cadastrado"),
            @ApiResponse(responseCode = "209", description = "Razão social já cadastrada"),
    })
    @PostMapping
    public void cadastrarEmpresa(@RequestBody @Valid CadastroEmpresaDTO dadosCadastrais) {
        service.cadastrarEmpresa(dadosCadastrais);
    }


    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "404", description = "Login ou senha incorretos"),
    })
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public Token getAccessToken(@RequestBody @Valid DadosLoginEmpresaDTO dadosLogin) {
        return new Token(service.getAccessToken(dadosLogin));
    }


    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Foto salva com sucesso"),
            @ApiResponse(responseCode = "403", description = "Acesso negado"),
    })
    @PreAuthorize("hasRole('empresa')")
    @PutMapping("/foto")
    public void salvarFoto(@RequestParam MultipartFile foto) throws IOException {
        service.salvarFoto(foto.getBytes());
    }


    @ApiResponses({
            @ApiResponse(responseCode = "200"),
    })
    @GetMapping("/foto/{id}")
    public ResponseEntity<byte[]> renderizarFoto(@PathVariable UUID id) {
        byte[] foto = service.buscarFotoEmpresa(id);
        return utils.renderizarFoto(foto);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200")
    })
    @GetMapping("/{id}")
    public PerfilEmpresa carregarPerfil(@PathVariable UUID id) {
        return service.carregarPerfil(id);
    }


    //REGISTRAR MODELO DE PROPOSTA
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Rascunho cadastrado com sucesso"),
            @ApiResponse(responseCode = "403", description = "Acesso negado"),
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('empresa')")
    @PostMapping("/rascunho")
    public ModeloDePropostaDTO cadastroModeloDeProposta(@RequestBody @Valid CadastroModeloDePropostaDTO dadosCadastrais) {
        return propostaService.registrarModelo(dadosCadastrais);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "403", description = "Acesso negado"),
    })
    @PreAuthorize("hasRole('empresa')")
    @GetMapping("/rascunho")
    public List<ModeloDePropostaDTO> buscarRascunhos() {
        return propostaService.buscarModelosEmpresa();
    }


    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "403", description = "Acesso negado"),
    })
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('empresa')")
    @DeleteMapping("/rascunho/{idRascunho}")
    public void deletarRascunho(@PathVariable UUID idRascunho) {
        propostaService.deletarRascunho(idRascunho);
    }
}
