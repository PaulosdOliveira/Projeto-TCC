package com.github.PaulosdOliveira.TCC.selectAspi.model.vaga;

import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Sexo;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Data
public class CardVagaDTO {

    private long id;
    private String nome_empresa;
    private String titulo;
    private String cidade;
    private String estado;
    private String salario;
    private String modelo;
    private String tipo_contrato;
    private String nivel;
    private boolean exclusivo_pcd;
    private String sexoExclusivo;
    private String periodo;

    public CardVagaDTO(Long id, String nome_empresa, String titulo, String cidade, String estado, Float salario, String modelo, String tipo_contrato, String nivel, boolean exclusivo_pcd, Sexo sexoExclusivo, String periodo) {
        this.id = id;
        this.nome_empresa = nome_empresa;
        this.titulo = titulo;
        this.cidade = cidade;
        this.estado = estado;
        this.salario = salario > 0 ? salario.toString() : "A combinar";
        this.modelo = modelo;
        this.tipo_contrato = tipo_contrato;
        this.nivel = nivel;
        this.exclusivo_pcd = exclusivo_pcd;
        this.sexoExclusivo = sexoExclusivo.getDescricao();
        this.periodo = periodo;
    }

}
