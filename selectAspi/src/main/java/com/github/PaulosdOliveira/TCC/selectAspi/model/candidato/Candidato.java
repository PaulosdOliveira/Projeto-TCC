package com.github.PaulosdOliveira.TCC.selectAspi.model.candidato;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;


@NoArgsConstructor
@Data
@Entity
public class Candidato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 14, unique = true)
    private String cpf;

    @Column(nullable = false, length = 300)
    private String senha;

    @Column()
    private byte[] foto;

    @Column(nullable = false, length = 120)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false, length = 18)
    private String tel;

    @Column(nullable = false, length = 254, unique = true)
    private String email;

    @Column()
    private byte[] curriculo;

    @Column(nullable = false)
    private boolean trabalhando;

    @Column(name = "estado", nullable = false)
    private String uf;

    @Column(name = "cidade", nullable = false)
    private String localidade;


    public Candidato(CadastroCandidatoDTO dadosCadastrais){
        BeanUtils.copyProperties(dadosCadastrais, this);
    }

}
