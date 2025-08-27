package com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@NoArgsConstructor
@Data
public class MensagemDTO {
    private UUID id;
    private UUID idEmpresa;
    private Long idCandidato;
    private String texto;
    private String horaEnvio;
    private String perfilRemetente;

    public MensagemDTO(UUID id, String texto, LocalDateTime horaEnvio, UUID idEmpresa, Long idCandidato, PerfilRemetente perfilRemetente) {
        this.id = id;
        this.texto = texto;
        int hora = horaEnvio.getHour();
        int minuto = horaEnvio.getMinute();
        this.horaEnvio = (hora > 9? hora : "0" + hora) + ":" + (minuto > 9? minuto : "0" + minuto);
        this.idEmpresa = idEmpresa;
        this.idCandidato = idCandidato;
        this.perfilRemetente = perfilRemetente.getPerfil();
    }


}
