package com.github.PaulosdOliveira.TCC.selectAspi.model.curso;

import lombok.Data;

import java.util.UUID;

@Data
public class CursoDTO {

    private final UUID id;
    private final String instituicao;
    private final String curso;
    private final short carga;
    private  String cargaHoraria;


    public CursoDTO(UUID id, String instituicao, String curso, short carga) {
        this.id = id;
        this.instituicao = instituicao;
        this.curso = curso;
        this.carga = carga;
        this.cargaHoraria = carga + " Horas";
    }
}
