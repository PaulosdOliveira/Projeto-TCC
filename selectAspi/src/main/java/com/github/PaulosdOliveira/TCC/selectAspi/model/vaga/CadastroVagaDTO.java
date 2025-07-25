package com.github.PaulosdOliveira.TCC.selectAspi.model.vaga;


import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Sexo;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.enums.Modelo;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.enums.Nivel;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.enums.TipoContrato;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class CadastroVagaDTO {

    @NotBlank(message = "Campo obrigatório")
    private String titulo;

    @NotBlank(message = "Campo obrigatório")
    private String descricao_vaga;

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

    @NotNull(message = "Campo obrigatório")
    private Long diasEmAberto;

    @NotNull(message = "Campo obrigatório")
    private Float salario;


    @NotNull(message = "Campo obrigatório")
    private Nivel nivel;

    @NotBlank(message = "Campo obrigatório")
    private String cep;

    @NotNull(message = "Campo obrigatório")
    private Modelo modelo;

    @NotNull(message = "MASCULINO, FEMININO OU TODOS")
    private Sexo exclusivoParaSexo;

    @NotNull(message = "Campo obrigatório")
    private Boolean exclusivoParaPcd;

    @NotNull(message = "Campo obrigatório")
    private TipoContrato tipoContrato;
}
