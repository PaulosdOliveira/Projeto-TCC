package com.github.PaulosdOliveira.TCC.selectAspi.model.vaga;

import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Sexo;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.enums.Modelo;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.enums.Nivel;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.enums.TipoContrato;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Data
public class ConsultaVagaDTO {

    private Long id;
    private UUID id_empresa;
    private String nome_empresa;
    private String titulo;
    private String[] descricao;
    private LocalDateTime dataHoraPublicacao;
    private LocalDateTime dataHoraEncerramento;
    private Float salario;
    private Nivel nivel;
    private String estado;
    private String cidade;
    private Modelo modelo;
    private Sexo ExclusivoParaSexo;
    private Boolean ExclusivoParaPcd;
    private TipoContrato tipoContrato;
    private boolean jaCandidatou;

    public ConsultaVagaDTO(VagaEmprego vaga) {
        BeanUtils.copyProperties(vaga, this);
        this.nome_empresa = vaga.getEmpresa().getNome();
        this.id_empresa = vaga.getEmpresa().getId();
        this.descricao = vaga.getDescricao().split("###");
    }

}
