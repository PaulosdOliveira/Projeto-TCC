package com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.candidato;

import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.enums.StatusCandidatura;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CandidaturaCandidato {
    private Long idVaga;
    private String tituloVaga;
    private String nomeEmpresa;
    private StatusCandidatura status;
    private Boolean finalizada;

    public CandidaturaCandidato(Long idVaga, String tituloVaga, String nomeEmpresa, StatusCandidatura status) {
        this.idVaga = idVaga;
        this.tituloVaga = tituloVaga;
        this.nomeEmpresa = nomeEmpresa;
        this.status = status;
        finalizada = !status.equals(StatusCandidatura.EM_ANALISE);
    }
}
