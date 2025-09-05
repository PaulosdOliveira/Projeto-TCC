package com.github.PaulosdOliveira.TCC.selectAspi.model.curso;

import lombok.Data;

import java.util.UUID;

@Data
public class CursoDTO {

    private final UUID id;
    private final String instituicao;
    private final String curso;
    private final String cargaHoraria;


    public CursoDTO(UUID id, String instituicao, String curso, short cargaHoraria) {
        this.id = id;
        this.instituicao = instituicao;
        this.curso = curso;
        this.cargaHoraria = cargaHoraria + " Horas";
    }
}
