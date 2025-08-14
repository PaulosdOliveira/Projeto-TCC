package com.github.PaulosdOliveira.TCC.selectAspi.model.candidato;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConsultaCandidatoDTO {

    private Long id;
    private String nome;
    private int idade;
    private String cidade;
    private String estado;


    public ConsultaCandidatoDTO(Long id, String nome, String cidade, String estado, LocalDate dataNascimento) {
        String[] nomeDividido = nome.split(" ");
        this.id = id;
        this.nome = nomeDividido.length > 1 ? nomeDividido[0] + " " + nomeDividido[nomeDividido.length - 1] : nomeDividido[0];
        this.cidade = cidade;
        this.estado = estado;
        this.idade = (int) ChronoUnit.YEARS.between(dataNascimento, LocalDateTime.now());
    }

}
