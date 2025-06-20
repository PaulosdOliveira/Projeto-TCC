package com.github.PaulosdOliveira.TCC.selectAspi.model.vaga;

import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Sexo;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.enums.Modelo;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.enums.Nivel;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.enums.TipoContrato;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import java.time.LocalDateTime;

@Data
public class ConsultaVagaDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private LocalDateTime dataHoraPublicacao;
    private LocalDateTime dataHoraEncerramento;
    private Float salario;
    private Boolean salarioACombinar;
    private Nivel nivel;
    private String estado;
    private String cidade;
    private Modelo modelo;
    private Sexo ExclusivoParaSexo;
    private Boolean ExclusivoParaPcd;
    private TipoContrato tipoContrato;

    public ConsultaVagaDTO(VagaEmprego vaga) {
        BeanUtils.copyProperties(vaga, this);
    }

}
