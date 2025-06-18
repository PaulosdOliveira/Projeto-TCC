package com.github.PaulosdOliveira.TCC.selectAspi.model.vaga;

import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.enums.Modelo;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.enums.Nivel;
import lombok.Data;

@Data
public class DadosConsultaVagaDTO {
    private String titulo;
    private String estado;
    private String cidade;
    private Nivel senioridade;
    private Modelo modelo;
}
