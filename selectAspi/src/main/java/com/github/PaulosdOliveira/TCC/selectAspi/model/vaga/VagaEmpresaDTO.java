package com.github.PaulosdOliveira.TCC.selectAspi.model.vaga;

import lombok.Data;

import static com.github.PaulosdOliveira.TCC.selectAspi.application.UtilsService.getTempoDecorrido;

import java.time.LocalDateTime;

@Data
public class VagaEmpresaDTO {
    private Long id;
    private String titulo;
    private String qtd_candidatos;
    private String tempo_decorrido;

    public VagaEmpresaDTO(Long id, String titulo, LocalDateTime dataHoraPublicacao, Long qtd_candidatos) {
        this.id = id;
        this.titulo = titulo;
        this.qtd_candidatos = qtd_candidatos + (qtd_candidatos != 1 ? " Candidaturas" : " Candidatura");
        this.tempo_decorrido = getTempoDecorrido(dataHoraPublicacao);
    }
}
