package com.github.PaulosdOliveira.TCC.selectAspi.model.vaga;

import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Sexo;
import com.github.PaulosdOliveira.TCC.selectAspi.model.localizacao.Cidade;
import com.github.PaulosdOliveira.TCC.selectAspi.model.localizacao.Estado;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.enums.TipoContrato;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.enums.Modelo;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.enums.Nivel;
import com.github.PaulosdOliveira.TCC.selectAspi.model.empresa.Empresa;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.CreatedDate;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import lombok.Data;

@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Data
@Entity
public class VagaEmprego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false, columnDefinition = "text")
    private String descricao;

    @Column(nullable = false)
    private String principais_atividades;

    @Column(nullable = false)
    private String requisitos;

    @Column(nullable = false)
    private String diferenciais;

    @Column(nullable = false)
    private String local_de_trabalho;

    @Column(nullable = false)
    private String horario;

    @Column(nullable = false, columnDefinition = "text")
    private String mensagemConvocacao;

    @Column(nullable = false, columnDefinition = "text")
    private String mensagemDispensa;

    @CreatedDate
    private LocalDateTime dataHoraPublicacao;

    private LocalDateTime dataHoraEncerramento;

    @JoinColumn
    @OneToOne
    private Empresa empresa;

    @Column(nullable = false)
    private Float salario;

    @Enumerated(EnumType.STRING)
    private Nivel nivel;

    @JoinColumn
    @ManyToOne
    private Estado estado;

    @JoinColumn
    @ManyToOne
    private Cidade cidade;

    @Enumerated(EnumType.STRING)
    private Modelo modelo;

    private boolean vagaAtiva;

    @Enumerated(EnumType.STRING)
    private Sexo exclusivoParaSexo;

    private Boolean exclusivoParaPcd;

    @Enumerated(EnumType.STRING)
    private TipoContrato tipoContrato;

    public VagaEmprego(CadastroVagaDTO dadosCadastrais, Empresa empresa) {
        int diasEmAberto = dadosCadastrais.getDiasEmAberto();
        if (diasEmAberto > 0) this.dataHoraEncerramento = LocalDateTime.now().plusDays(diasEmAberto);
        this.estado = new Estado(dadosCadastrais.getIdEstado());
        this.cidade = new Cidade(dadosCadastrais.getIdCidade());
        this.empresa = empresa;
        this.vagaAtiva = true;
        BeanUtils.copyProperties(dadosCadastrais, this);
    }


    public VagaEmprego(Long id) {
        this.id = id;
    }

    public VagaEmprego(Long id, boolean vagaAtiva, LocalDateTime dataEncerramento) {
        this.id = id;
        this.vagaAtiva = vagaAtiva;
        this.dataHoraEncerramento = dataEncerramento;
    }

    @Override
    public String toString() {
        return "VagaEmprego{" +
               "id=" + id +
               ", titulo='" + titulo + '\'' +
               ", descricao='" + descricao + '\'' +
               ", principais_atividades='" + principais_atividades + '\'' +
               ", requisitos='" + requisitos + '\'' +
               ", diferenciais='" + diferenciais + '\'' +
               ", local_de_trabalho='" + local_de_trabalho + '\'' +
               ", horario='" + horario + '\'' +
               ", mensagemConvocacao='" + mensagemConvocacao + '\'' +
               ", mensagemDispensa='" + mensagemDispensa + '\'' +
               ", dataHoraPublicacao=" + dataHoraPublicacao +
               ", dataHoraEncerramento=" + dataHoraEncerramento +
               ", salario=" + salario +
               ", nivel=" + nivel +
               ", estado=" + estado +
               ", cidade=" + cidade +
               ", modelo=" + modelo +
               ", vagaAtiva=" + vagaAtiva +
               ", exclusivoParaSexo=" + exclusivoParaSexo +
               ", exclusivoParaPcd=" + exclusivoParaPcd +
               ", tipoContrato=" + tipoContrato +
               '}';
    }
}
