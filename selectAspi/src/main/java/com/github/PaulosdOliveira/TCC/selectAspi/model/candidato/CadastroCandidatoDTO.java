package com.github.PaulosdOliveira.TCC.selectAspi.model.candidato;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@NoArgsConstructor
@Data
public class CadastroCandidatoDTO {

    @CPF(message = "Digite um CPF válida")
    @NotBlank( message = "Informe o seu CPF")
    private String cpf;

    @NotBlank(message = "Informe a sua senha")
    private String senha;

    @NotBlank(message = "Informe o seu nome")
    private String nome;

    @NotBlank(message = "Crie uma descrição para o seu perfil")
    private String descricao;

    private String tel;

    @Email(message = "Digite um email válido")
    @NotBlank(message = "Informe o seu email")
    private String email;

    private byte[] curriculo;

    @NotNull(message = "Informe se está trabalho atualmente")
    private boolean trabalhando;

    @NotBlank(message = "Informe um cep de sua cidade")
    private String cep;


}
