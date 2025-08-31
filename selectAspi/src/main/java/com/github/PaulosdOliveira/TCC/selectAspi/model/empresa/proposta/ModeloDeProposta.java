package com.github.PaulosdOliveira.TCC.selectAspi.model.empresa.proposta;

import com.github.PaulosdOliveira.TCC.selectAspi.model.empresa.Empresa;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity
public class ModeloDeProposta {

    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    private UUID id;
    private String titulo;
    private String descricao;
    @JoinColumn
    @ManyToOne
    private Empresa empresa;


    public ModeloDeProposta(CadastroModeloDePropostaDTO dadosCadastrais, String idEmpresa) {
        this.titulo = dadosCadastrais.getTitulo();
        this.descricao = dadosCadastrais.getDescricao();
        this.empresa = new Empresa(idEmpresa);
    }
}
