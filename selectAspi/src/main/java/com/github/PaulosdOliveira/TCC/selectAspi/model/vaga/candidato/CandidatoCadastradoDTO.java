package com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.candidato;

import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.ConsultaCandidatoDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.enums.StatusCandidatura;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class CandidatoCadastradoDTO extends ConsultaCandidatoDTO {

    private StatusCandidatura status;

    public CandidatoCadastradoDTO(Long id, String nome, String cidade, String estado, LocalDate dataNascimento, StatusCandidatura status) {
        super(id, nome, cidade, estado, dataNascimento);
        this.status = status;
    }
}
