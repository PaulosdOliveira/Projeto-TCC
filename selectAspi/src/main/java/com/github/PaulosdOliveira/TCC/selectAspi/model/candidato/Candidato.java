package com.github.PaulosdOliveira.TCC.selectAspi.model.candidato;

import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.QualificacaoUsuario;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Arrays;
import java.util.List;


@NoArgsConstructor
@Data
@Entity
@Table(name = "candidatos")
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

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    private Boolean pcd;

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

    @OneToMany(mappedBy = "candidato", fetch = FetchType.EAGER)
    private List<QualificacaoUsuario> qualificacoes;


    public Candidato(CadastroCandidatoDTO dadosCadastrais, String senhaCriptografada) {
        BeanUtils.copyProperties(dadosCadastrais, this);
        this.senha = senhaCriptografada;
    }


    @Override
    public String toString() {
        return "Candidato{" +
               "nome='" + nome + '\'' +
               ", descricao='" + descricao + '\'' +
               ", tel='" + tel + '\'' +
               ", email='" + email + '\'' +
               ", trabalhando=" + trabalhando +
               ", uf='" + uf + '\'' +
               ", localidade='" + localidade + '\'' +
               ", qualificacoes=" + qualificacoes +
               ", id=" + id +
               ", cpf='" + cpf + '\'' +
               '}';
    }
}
