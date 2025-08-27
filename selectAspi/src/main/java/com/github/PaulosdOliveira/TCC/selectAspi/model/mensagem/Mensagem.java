package com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem;

import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Candidato;
import com.github.PaulosdOliveira.TCC.selectAspi.model.empresa.Empresa;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    @JoinColumn
    @ManyToOne
    private Empresa empresa;

    @JoinColumn
    @ManyToOne
    private Candidato candidato;

    @Enumerated(EnumType.STRING)
    @Column(name = "enviado_por")
    private PerfilRemetente perfilRemetente;

    public Mensagem(CadastroMensagemDTO dto) {
        this.texto = dto.getTexto();
        this.candidato = new Candidato(dto.getIdCandidato());
        this.empresa = new Empresa(dto.getIdEmpresa().toString());
        this.perfilRemetente = PerfilRemetente.ofName(dto.getPerfilRemetente());
    }
}
