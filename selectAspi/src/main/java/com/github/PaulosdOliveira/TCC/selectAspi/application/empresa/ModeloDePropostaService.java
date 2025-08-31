package com.github.PaulosdOliveira.TCC.selectAspi.application.empresa;

import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.ModeloDePropostaRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.model.empresa.proposta.CadastroModeloDePropostaDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.empresa.proposta.ModeloDeProposta;
import com.github.PaulosdOliveira.TCC.selectAspi.model.empresa.proposta.ModeloDePropostaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class ModeloDePropostaService {

    @Autowired
    private ModeloDePropostaRepository repository;

    public ModeloDePropostaDTO registrarModelo(CadastroModeloDePropostaDTO dadosCadastrais) {
        String idEmpresaLogada = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        var racunho = repository.save(new ModeloDeProposta(dadosCadastrais, idEmpresaLogada));
        return  new ModeloDePropostaDTO(racunho.getId(), racunho.getTitulo(), racunho.getTitulo());
    }

    public List<ModeloDePropostaDTO> buscarModelosEmpresa() {
        String idEmpresaLogada = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        return repository.buscarRascunhos(UUID.fromString(idEmpresaLogada));
    }


    public void deletarRascunho(UUID id){
        repository.deleteById(id);
    }

}
