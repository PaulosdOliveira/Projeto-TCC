package com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.candidato;

import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.enums.StatusCandidatura;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@Data
public class CandidaturaCandidato {
    private Long idVaga;
    private String tituloVaga;
    private String nomeEmpresa;
    private UUID idEmpresa;
    private String status;
    private Boolean finalizada;

    public CandidaturaCandidato(Long idVaga, String tituloVaga, String nomeEmpresa, UUID idEmpresa, StatusCandidatura status) {
        this.idVaga = idVaga;
        this.tituloVaga = tituloVaga;
        this.nomeEmpresa = nomeEmpresa;
        this.idEmpresa = idEmpresa;
        this.status = status.getTexto();
        finalizada = !status.equals(StatusCandidatura.EM_ANALISE);
    }
}
