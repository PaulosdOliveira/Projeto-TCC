package com.github.PaulosdOliveira.TCC.selectAspi.exception;

import com.github.PaulosdOliveira.TCC.selectAspi.model.experiencia.Experiencia;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TempoExperienciaxception extends RuntimeException {

    private Experiencia experiencia;

    public TempoExperienciaxception(String message, Experiencia experiencia) {
        super(message);
        this.experiencia = experiencia;
    }
}
