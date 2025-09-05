package com.github.PaulosdOliveira.TCC.selectAspi.application.experiencia;


import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.ExperienciaRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Candidato;
import com.github.PaulosdOliveira.TCC.selectAspi.model.experiencia.CadastroExperienciaDTO;
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


    public void cadastrarExperiencia(List<CadastroExperienciaDTO> experienciasDTO, Candidato candidato) {
       var experiencias = experienciasDTO.stream().map(dto -> new Experiencia(dto, candidato)).toList();
        repository.saveAll(experiencias);
    }

    public List<ExperienciaDTO> buscarExperienciasCandidato(Long idCandidato) {
        return repository.buscarExperienciasUsuario(idCandidato);
    }

    public void deletarExperiencia(UUID id){
        repository.deletarExperiencia(id, getIdCandidatoLogado());
    }
}
