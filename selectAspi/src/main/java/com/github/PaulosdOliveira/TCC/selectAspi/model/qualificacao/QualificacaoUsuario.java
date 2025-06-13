package com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao;

import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Candidato;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class QualificacaoUsuario {

    @EmbeddedId
    private ChaveCompostaQualificacao id;

    @ManyToOne
    @MapsId("candidato")
    @JoinColumn
    private Candidato candidato;

    @Enumerated(EnumType.STRING)
    private Nivel nivel;


    @Override
    public String toString(){
        return  id.getQualificacao().getNome() + " Nivel: " + nivel;
    }
}
