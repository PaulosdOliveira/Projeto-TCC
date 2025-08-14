package com.github.PaulosdOliveira.TCC.selectAspi.model.candidato;

import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.ConsultaQualificacaoUsuario;
import lombok.Data;
import java.util.List;

@Data
public class DadosConsultaCandidatoDTO {
    private String idEstado;
    private String idCidade;
    private List<ConsultaQualificacaoUsuario> qualificacoes;
    private String sexo;
    private boolean pcd;
    private Boolean trabalhando;
}
