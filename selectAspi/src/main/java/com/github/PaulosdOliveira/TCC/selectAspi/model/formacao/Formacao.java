package com.github.PaulosdOliveira.TCC.selectAspi.model.formacao;

import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Candidato;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity
public class Formacao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private Candidato candidato;

    @Column(length = 150, nullable = false)
    private String instituicao;

    @Column(length = 150, nullable = false)
    private String curso;

    @Enumerated(EnumType.STRING)
    private Nivel nivel;

    @Column(nullable = false)
    private Date inicio;

    @Column(nullable = false)
    private Date fim;

    public Formacao(String instituicao, String curso, Nivel nivel, Date inicio, Date fim) {
        this.instituicao = instituicao;
        this.curso = curso;
        this.nivel = nivel;
        this.inicio = inicio;
        this.fim = fim;
    }

}
