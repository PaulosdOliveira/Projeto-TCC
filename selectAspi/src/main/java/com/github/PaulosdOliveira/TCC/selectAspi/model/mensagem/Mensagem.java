package com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(columnDefinition = "text")
    private String texto;

    @CreatedDate
    private LocalDateTime dataHoraEnvio;

    @Column(nullable = false, name = "empresa_id")
    private UUID idEmpresa;

    @Column(nullable = false, name = "candidato_id")
    private Long idCandidato;

    public Mensagem(MensagemDTO dto) {
        BeanUtils.copyProperties(dto, this);
    }
}
