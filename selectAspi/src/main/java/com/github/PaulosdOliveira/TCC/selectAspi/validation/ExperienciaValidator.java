package com.github.PaulosdOliveira.TCC.selectAspi.validation;

import com.github.PaulosdOliveira.TCC.selectAspi.exception.TempoExperienciaxception;
import com.github.PaulosdOliveira.TCC.selectAspi.model.experiencia.Experiencia;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class ExperienciaValidator {

    public void validar(Experiencia experiencia) {
        fimIsGreatest(experiencia);
        tempoInsuficiente(experiencia);
    }

    private void fimIsGreatest(Experiencia experiencia) {
        if (experiencia.getInicio().isAfter(experiencia.getFim()))
            throw new TempoExperienciaxception("A data de inicio não pode ser maior que a final: ", experiencia);
    }

    private void tempoInsuficiente(Experiencia experiencia) {
        if (ChronoUnit.MONTHS.between(experiencia.getInicio(), experiencia.getFim()) < 1)
            throw new TempoExperienciaxception("Suas experiências precisam ser de ao menos 1 mês: ", experiencia);
        ;
    }
}
