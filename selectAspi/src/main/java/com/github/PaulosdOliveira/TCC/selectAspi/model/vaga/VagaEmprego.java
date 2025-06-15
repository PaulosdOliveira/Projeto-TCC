package com.github.PaulosdOliveira.TCC.selectAspi.model.vaga;

import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Sexo;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.enums.TipoContrato;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.enums.Modelo;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.enums.Nivel;
import com.github.PaulosdOliveira.TCC.selectAspi.model.empresa.Empresa;
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

    @CreatedDate
    private LocalDateTime dataHoraPublicacao;

    private LocalDateTime dataHoraEncerramento;

    @JoinColumn
    @OneToOne
    private Empresa empresa;

    @Column(nullable = false)
    private Float salario;

    @Column(name = "salario_a_combinar")
    private Boolean salarioACombinar;

    @Enumerated(EnumType.STRING)
    private Nivel nivel;

    @Column(nullable = false, length = 40)
    private String cidade;

    @Enumerated(EnumType.STRING)
    private Modelo modelo;

    private boolean vagaAtiva;

    @Enumerated(EnumType.STRING)
    private Sexo ExclusivaParaSexo;

    private Boolean ExclusivoParaPcd;

    @Enumerated(EnumType.STRING)
    private TipoContrato tipoContrato;

    public VagaEmprego(CadastroVagaDTO  dadosCadastrais, String cidade, Empresa empresa){
        Long diasEmAberto = dadosCadastrais.getDiasEmAberto();
        if(dadosCadastrais.getSalario() == 0) this.salarioACombinar = true;
        if(diasEmAberto > 0) this.dataHoraEncerramento = LocalDateTime.now().plusDays(diasEmAberto);
        this.cidade = cidade;
        this.empresa = empresa;
        this.vagaAtiva = true;
        BeanUtils.copyProperties(dadosCadastrais, this);
    }
}
