package com.github.PaulosdOliveira.TCC.selectAspi.model.formacao;

import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Candidato;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity
public class Formacao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private Candidato candidato;


    @Column(length = 150, nullable = false)
    private String instituicao;

    @NotBlank(message = "Campo obrigatório")
    @Column(length = 150, nullable = false)
    private String curso;

    @NotNull(message = "Campo obrigatório")
    @Enumerated(EnumType.STRING)
    private Nivel nivel;

    @NotNull(message = "Campo obrigatório")
    @Column(nullable = false)
    private LocalDate inicio;

    @NotNull(message = "Campo obrigatório")
    @Column(nullable = false)
    private LocalDate fim;

    public Formacao(CadastroFormacaoDTO dadosCadastrais, Candidato candidato) {
        BeanUtils.copyProperties(dadosCadastrais, this);
        this.candidato = candidato;
    }

}
