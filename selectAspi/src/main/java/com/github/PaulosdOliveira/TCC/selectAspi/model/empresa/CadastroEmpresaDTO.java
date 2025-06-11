package com.github.PaulosdOliveira.TCC.selectAspi.model.empresa;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CadastroEmpresaDTO {

    //@CNPJ
    @NotBlank(message = "Informe o CNPJ da empresa")
    private String cnpj;

    @NotBlank(message = "Informe o nome da empresa")
    private String nome;

    @Email(message = "Email inválido")
    @NotBlank(message = "Informe o email da empresa")
    private String email;

    @NotBlank(message = "Crie uma senha")
    private String senha;

    private String descricao;

    private byte[] foto;

    private byte[] capa;
}
