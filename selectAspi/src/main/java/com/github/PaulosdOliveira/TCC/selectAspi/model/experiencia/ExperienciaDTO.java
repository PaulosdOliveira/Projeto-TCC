package com.github.PaulosdOliveira.TCC.selectAspi.model.experiencia;


import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

import static com.github.PaulosdOliveira.TCC.selectAspi.application.UtilsService.getTempoDeExperiencia;

@Data
public class ExperienciaDTO {
    private UUID id;
    private String empresa;
    private String cargo;
    private String descricao;
    private String duracao;


    public ExperienciaDTO(UUID id, String empresa, String cargo, String descricao, LocalDate inicio, LocalDate fim) {
        this.id = id;
        this.empresa = empresa;
        this.cargo = cargo;
        this.descricao = descricao;
        this.duracao = getTempoDeExperiencia(inicio, fim);
    }
}
