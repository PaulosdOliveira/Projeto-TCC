package com.github.PaulosdOliveira.TCC.selectAspi.model.vaga;

import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Sexo;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.enums.Modelo;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.enums.Nivel;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.enums.TipoContrato;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;


@NoArgsConstructor
@Data
public class ConsultaVagaDTO {

    private Long id;
    private UUID id_empresa;
    private String nome_empresa;
    private String titulo;
    private String descricao;
    private String principais_atividades;
    private String requisitos;
    private String diferenciais;
    private String local_de_trabalho;
    private String horario;
    private String mensagemConvocacao;
    private String mensagemDispensa;
    private LocalDateTime dataHoraPublicacao;
    private LocalDateTime dataHoraEncerramento;
    private Long diasEmAberto;
    private Float salario;
    private Nivel nivel;
    private String estado;
    private String cidade;
    private Modelo modelo;
    private Sexo exclusivoParaSexo;
    private Boolean exclusivoParaPcd;
    private TipoContrato tipoContrato;
    private boolean jaCandidatou;


    // CONSTRUTOR USADO NO REPOSITORY
    public ConsultaVagaDTO(VagaEmprego vaga) {
        BeanUtils.copyProperties(vaga, this);
        this.nome_empresa = vaga.getEmpresa().getNome();
        this.id_empresa = vaga.getEmpresa().getId();
        this.cidade = vaga.getCidade().getNome();
        this.estado = vaga.getEstado().getSigla();
        this.diasEmAberto = dataHoraEncerramento != null?
                ChronoUnit.DAYS.between(dataHoraPublicacao, dataHoraEncerramento) : 0;
        System.out.println(vaga.getPrincipais_atividades());
        System.out.println(this.principais_atividades);
    }


}
