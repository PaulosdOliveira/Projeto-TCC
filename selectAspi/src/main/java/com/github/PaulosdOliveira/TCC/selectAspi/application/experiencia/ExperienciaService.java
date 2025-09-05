package com.github.PaulosdOliveira.TCC.selectAspi.application.experiencia;


import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.ExperienciaRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Candidato;
import com.github.PaulosdOliveira.TCC.selectAspi.model.experiencia.Experiencia;
import com.github.PaulosdOliveira.TCC.selectAspi.model.experiencia.ExperienciaDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.validation.ExperienciaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.github.PaulosdOliveira.TCC.selectAspi.application.UtilsService.getIdCandidatoLogado;

import java.util.List;
import java.util.UUID;

@Service
public class ExperienciaService {

    @Autowired
    private ExperienciaRepository repository;

    @Autowired
    private ExperienciaValidator validator;


    public void cadastrarExperiencia(List<Experiencia> experiencias, Candidato candidato) {
        experiencias.removeIf(e -> {
            validator.validar(e);
            e.setCandidato(candidato);
            return false;
        });
        repository.saveAll(experiencias);
    }

    public List<ExperienciaDTO> buscarExperienciasCandidato(Long idCandidato) {
        return repository.buscarExperienciasUsuario(idCandidato);
    }

    public void deletarExperiencia(UUID id){
        repository.deletarExperiencia(id, getIdCandidatoLogado());
    }
}
