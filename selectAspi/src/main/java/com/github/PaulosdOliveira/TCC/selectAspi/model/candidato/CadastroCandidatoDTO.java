package com.github.PaulosdOliveira.TCC.selectAspi.model.candidato;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class CadastroCandidatoDTO {

    //@CPF(message = "Digite um CPF válida")
    @NotBlank(message = "Informe o seu CPF")
    private String cpf;

    @Email(message = "Digite um email válido")
    @NotBlank(message = "Informe o seu email")
    private String email;

    @NotBlank(message = "Informe a sua senha")
    private String senha;

    @NotBlank(message = "Informe o seu nome")
    private String nome;


    @NotNull(message = "Informe a sua data de nascimento")
    @Past(message = "A data de nascimento não pode ser futura")
    private LocalDate dataNascimento;

    @NotNull(message = "Informe o seu sexo")
    private Sexo sexo;

    private Boolean pcd;

    @NotBlank(message = "Crie uma descrição para o seu perfil")
    private String descricao;

    private String tel;

    @NotNull(message = "Informe se está trabalho atualmente")
    private boolean trabalhando;

    @NotNull(message = "Obrigatório")
    private int idEstado;

    @NotNull(message = "Obrigatório")
    private int idCidade;


}
