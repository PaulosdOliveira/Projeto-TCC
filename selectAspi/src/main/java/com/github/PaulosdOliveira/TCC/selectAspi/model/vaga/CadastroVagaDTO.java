package com.github.PaulosdOliveira.TCC.selectAspi.model.vaga;


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
    private String descricao;

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

    @NotNull(message = "Campo obrigatório")
    private TipoContrato tipoContrato;
}
