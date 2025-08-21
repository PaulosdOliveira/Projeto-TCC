package com.github.PaulosdOliveira.TCC.selectAspi.model.vaga;


import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Sexo;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.enums.Modelo;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.enums.Nivel;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.enums.TipoContrato;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.temporal.ChronoUnit;


@NoArgsConstructor
@Data
public class CadastroVagaDTO {

    @NotBlank(message = "Campo obrigatório")
    private String titulo;

    @NotBlank(message = "Campo obrigatório")
    private String descricao;

    @NotBlank(message = "Campo obrigatório")
    private String principais_atividades;

    @NotBlank(message = "Campo obrigatório")
    private String requisitos;

    @NotBlank(message = "Campo obrigatório")
    private String diferenciais;

    @NotBlank(message = "Campo obrigatório")
    private String local_de_trabalho;

    @NotBlank(message = "Campo obrigatório")
    private String horario;

    @NotBlank(message = "Campo obrigatório")
    private String mensagemConvocacao;

    @NotBlank(message = "Campo obrigatório")
    private String mensagemDispensa;

    @NotNull(message = "Campo obrigatório")
    private int diasEmAberto;

    @NotNull(message = "Campo obrigatório")
    private Float salario;


    @NotNull(message = "Campo obrigatório")
    private Nivel nivel;

    @NotNull(message = "Campo obrigatório")
    private Integer idEstado;

    @NotNull(message = "Campo obrigatório")
    private Integer idCidade;

    @NotNull(message = "Campo obrigatório")
    private Modelo modelo;

    @NotNull(message = "MASCULINO, FEMININO OU TODOS")
    private Sexo exclusivoParaSexo;

    @NotNull(message = "Campo obrigatório")
    private Boolean exclusivoParaPcd;

    @NotNull(message = "Campo obrigatório")
    private TipoContrato tipoContrato;

    public CadastroVagaDTO(VagaEmprego vaga) {
        BeanUtils.copyProperties(vaga, this);
        this.diasEmAberto = vaga.getDataHoraEncerramento() != null ?
                (int) ChronoUnit.DAYS.between(vaga.getDataHoraPublicacao(), vaga.getDataHoraEncerramento()) : 0;
        this.idCidade = vaga.getCidade().getId();
        this.idEstado = vaga.getEstado().getId();
    }
}
