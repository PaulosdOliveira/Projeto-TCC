package com.github.PaulosdOliveira.TCC.selectAspi.model.candidato;

import com.github.PaulosdOliveira.TCC.selectAspi.model.curso.CadastroCursoDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.experiencia.CadastroExperienciaDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.formacao.CadastroFormacaoDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.QualificacaoUsuarioDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Data
public class EdicaoCandidatoDTO {
    private String nome;
    private LocalDate dataNascimento;
    private String descricao;
    private String tel;
    private int idEstado;
    private int idCidade;
    private boolean pcd;
    private boolean trabalhando;
    private Sexo sexo;
    private List<QualificacaoUsuarioDTO> qualificacoes;
    private List<CadastroFormacaoDTO> formacoes;
    private List<CadastroExperienciaDTO> experiencias;
    private List<CadastroCursoDTO> cursos;

    public EdicaoCandidatoDTO(String nome, LocalDate dataNascimento, String descricao, String tel, boolean pcd, boolean trabalhando, Sexo sexo,
                              int idCidade, int idEstado) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.descricao = descricao;
        this.tel = tel;
        this.pcd = pcd;
        this.idCidade = idCidade;
        this.idEstado = idEstado;
        this.trabalhando = trabalhando;
        this.sexo = sexo;
    }


}
