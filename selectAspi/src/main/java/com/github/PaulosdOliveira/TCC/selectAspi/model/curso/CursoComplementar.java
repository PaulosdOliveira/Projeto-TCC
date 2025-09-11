package com.github.PaulosdOliveira.TCC.selectAspi.model.curso;

import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Candidato;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

@NoArgsConstructor
@Data
@Table(name = "cursos")
@Entity
public class CursoComplementar {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn
    private Candidato candidato;

    @Column(nullable = false, length = 150)
    private String instituicao;

    @Column(nullable = false, length = 100)
    private String curso;


    @Column(nullable = false)
    private short cargaHoraria;

    public CursoComplementar(CadastroCursoDTO dadosCadastrais, Candidato candidato) {
        BeanUtils.copyProperties(dadosCadastrais, this);
        this.candidato = candidato;
    }
}
