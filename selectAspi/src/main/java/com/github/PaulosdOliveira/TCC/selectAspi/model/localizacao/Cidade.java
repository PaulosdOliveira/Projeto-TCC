package com.github.PaulosdOliveira.TCC.selectAspi.model.localizacao;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @JoinColumn
    @ManyToOne
    private Estado estado;

    public Cidade(Integer id) {
        this.id = id;
    }
}
