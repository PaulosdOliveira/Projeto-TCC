package com.github.PaulosdOliveira.TCC.selectAspi.application.vaga;

import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.VagaEmpregoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
public class VagaScheduler {

    @Autowired
    private VagaEmpregoRepository repository;

    @Transactional
    @Scheduled(cron = "0 38 10 * * *")
    public void desativarVagasExpiradas(){
        System.out.println("Limpesa sendo realizada");
        var vagas = repository.findAtivas();
        vagas.removeIf(vaga -> {
            if(vaga.getDataHoraEncerramento().isBefore(LocalDateTime.now())) vaga.setVagaAtiva(false);
            return false;
        });

    }
}
