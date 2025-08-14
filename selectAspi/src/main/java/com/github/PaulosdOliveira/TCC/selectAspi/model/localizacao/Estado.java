package com.github.PaulosdOliveira.TCC.selectAspi.model.localizacao;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String sigla;

    public Estado(Integer id) {
        this.id = id;
    }
}
