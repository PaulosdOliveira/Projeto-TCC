package com.github.PaulosdOliveira.TCC.selectAspi.application.vaga;

import com.github.PaulosdOliveira.TCC.selectAspi.application.candidato.LocalizacaoService;
import com.github.PaulosdOliveira.TCC.selectAspi.application.empresa.EmpresaService;
import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.VagaEmpregoRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.CadastroVagaDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.VagaEmprego;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class VagaEmpregoService {

    private final VagaEmpregoRepository repository;
    private final EmpresaService empresaService;
    private final LocalizacaoService localizacaoService;


    public void cadastrarVaga(CadastroVagaDTO dadosCadastrais) {
        //Long id = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getCredentials().toString());
        Long id = 1L;
        var empresaLogada = empresaService.findById(id);
        String cidade = localizacaoService.buscarLocalizacaoPorCep(dadosCadastrais.getCep()).getLocalidade();
        VagaEmprego vaga = new VagaEmprego(dadosCadastrais, cidade, empresaLogada);
        repository.save(vaga);
    }


}
