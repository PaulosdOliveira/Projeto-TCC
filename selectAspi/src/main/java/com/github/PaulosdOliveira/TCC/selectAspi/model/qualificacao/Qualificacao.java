package com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Qualificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Informe o nome da qualificação")
    @Column(nullable = false, name = "nome_qualificacao", unique = true)
    private String nome;

    public Qualificacao(Long id) {
        this.id = id;
    }
}
