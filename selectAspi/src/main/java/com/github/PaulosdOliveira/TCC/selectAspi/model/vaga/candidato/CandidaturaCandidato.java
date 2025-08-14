package com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.candidato;

import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.enums.StatusCandidatura;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CandidaturaCandidato {
    private  Long idVaga;
    private String tituloVaga;
    private String nomeEmpresa;
    private StatusCandidatura status;
}
