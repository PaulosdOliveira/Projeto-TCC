package com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@NoArgsConstructor
@Data
public class MensagemDTO {
    private UUID id;
    private String texto;
    private String horaEnvio;

    public MensagemDTO(UUID id, String texto, LocalDateTime horaEnvio) {
        this.id = id;
        this.texto = texto;
        this.horaEnvio = horaEnvio.getHour() + ":" + horaEnvio.getMinute();
    }
}
