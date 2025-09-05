package com.github.PaulosdOliveira.TCC.selectAspi.application.curso;

import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.CursoRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Candidato;
import com.github.PaulosdOliveira.TCC.selectAspi.model.curso.CadastroCursoDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.curso.CursoComplementar;
import com.github.PaulosdOliveira.TCC.selectAspi.model.curso.CursoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.github.PaulosdOliveira.TCC.selectAspi.application.UtilsService.getIdCandidatoLogado;

import java.util.List;
import java.util.UUID;

@Service
public class CursoService {

    @Autowired
    private CursoRepository repository;


    public void cadastrarCursos(List<CadastroCursoDTO> cursosDTO, Candidato candidato) {
       var cursos =  cursosDTO.stream().map(dto -> new CursoComplementar(dto, candidato)).toList();
        repository.saveAll(cursos);
    }

    public List<CursoDTO> buscarCursosCandidato(Long idCandidato) {
        return repository.buscarCursosCandidato(idCandidato);
    }


    public void deletarCurso(UUID id) {
        repository.deletarCurso(id, getIdCandidatoLogado());
    }
}
