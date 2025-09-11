package com.github.PaulosdOliveira.TCC.selectAspi.model.experiencia;

import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Candidato;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

@NoArgsConstructor
@Data
@Entity
public class Experiencia {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JoinColumn
    @ManyToOne
    private Candidato candidato;

    @Column(nullable = false, length = 150)
    private String empresa;

    @Column(nullable = false, length = 100)
    private String cargo;

    @Column(nullable = false, columnDefinition = "text")
    private String descricao;


    @Column(nullable = false, length = 30)
    private String duracao;


    public Experiencia(CadastroExperienciaDTO dadosCdastrais, Candidato candidato) {
        BeanUtils.copyProperties(dadosCdastrais, this);
        this.candidato = candidato;
    }

    @Override
    public String toString() {
        return "Experiencia{" +
               "empresa: '" + empresa + '\'' +
               ", cargo: '" + cargo + '\'' +
               ", descricao: '" + descricao + '\'' +

               '}';
    }
}
