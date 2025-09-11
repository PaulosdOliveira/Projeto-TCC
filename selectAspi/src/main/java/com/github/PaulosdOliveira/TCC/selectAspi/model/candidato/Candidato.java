package com.github.PaulosdOliveira.TCC.selectAspi.model.candidato;

import com.github.PaulosdOliveira.TCC.selectAspi.model.localizacao.Cidade;
import com.github.PaulosdOliveira.TCC.selectAspi.model.localizacao.Estado;
import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.QualificacaoUsuario;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import java.time.LocalDate;
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

    @Column(nullable = false)
    private LocalDate dataNascimento;

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

    @JoinColumn
    @ManyToOne
    private Estado estado;

    @JoinColumn
    @ManyToOne
    private Cidade cidade;



    @OneToMany(mappedBy = "id.candidato", fetch = FetchType.EAGER)
    private List<QualificacaoUsuario> qualificacoes;


    public Candidato(CadastroCandidatoDTO dadosCadastrais) {
        BeanUtils.copyProperties(dadosCadastrais, this);
        this.cidade = new Cidade(dadosCadastrais.getIdCidade());
        this.estado = new Estado(dadosCadastrais.getIdEstado());
    }

    public void carregarNovosDados(EdicaoCandidatoDTO novosDados) {
        BeanUtils.copyProperties(novosDados, this);
        this.cidade = new Cidade(novosDados.getIdCidade());
        this.estado = new Estado(novosDados.getIdEstado());
    }

    public Candidato(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Candidato{" +
               "nome='" + nome + '\'' +
               ", descricao='" + descricao + '\'' +
               ", tel='" + tel + '\'' +
               ", email='" + email + '\'' +
               ", trabalhando=" + trabalhando +
               ", qualificacoes="  +
               ", id=" + id +
               ", cpf='" + cpf + '\'' +
               '}';
    }
}
