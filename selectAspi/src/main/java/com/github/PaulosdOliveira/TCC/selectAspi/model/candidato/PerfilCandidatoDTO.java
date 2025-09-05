package com.github.PaulosdOliveira.TCC.selectAspi.model.candidato;

import com.github.PaulosdOliveira.TCC.selectAspi.model.curso.CursoComplementar;
import com.github.PaulosdOliveira.TCC.selectAspi.model.curso.CursoDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.experiencia.Experiencia;
import com.github.PaulosdOliveira.TCC.selectAspi.model.experiencia.ExperienciaDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.formacao.Formacao;
import com.github.PaulosdOliveira.TCC.selectAspi.model.formacao.FormacaoDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.localizacao.Cidade;
import com.github.PaulosdOliveira.TCC.selectAspi.model.localizacao.Estado;
import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.QualificacaoUsuario;
import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.QualificacoesSalvas;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@NoArgsConstructor
@Data
public class PerfilCandidatoDTO {

    private Long id;
    private String nome;
    private int idade;
    private Boolean pcd;
    private String descricao;
    private String tel;
    private String email;
    private boolean trabalhando;
    private String estado;
    private String cidade;
    private List<QualificacoesSalvas> qualificacoes;
    private List<ExperienciaDTO> experiencias;
    private List<CursoDTO> cursos;
    private List<FormacaoDTO> formacoes;


    public PerfilCandidatoDTO(Long id, String nome, String descricao, String tel, String email,
                              boolean trabalhando, Boolean pcd, Cidade cidade, Estado estado, LocalDate dataNascimento
    ) {
        this.pcd = pcd;
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.tel = tel;
        this.email = email;
        this.trabalhando = trabalhando;
        this.cidade = cidade.getNome();
        this.estado = estado.getSigla();
        this.idade = (int) ChronoUnit.YEARS.between(dataNascimento, LocalDateTime.now());
    }

    public void completarPerfil(List<QualificacoesSalvas> qualificacoes, List<ExperienciaDTO> experiencias, List<CursoDTO> cursos, List<FormacaoDTO> formacoes) {
        this.qualificacoes = qualificacoes;
        this.experiencias = experiencias;
        this.cursos = cursos;
        this.formacoes = formacoes;
    }
}
