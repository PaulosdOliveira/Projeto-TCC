package com.github.PaulosdOliveira.TCC.selectAspi.model.candidato;

import lombok.Data;

@Data
public class ConsultaCandidatoDTO {

    private String nome;

    public ConsultaCandidatoDTO(Candidato candidato) {
        this.nome = candidato.getNome();
    }

}
