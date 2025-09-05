package com.github.PaulosdOliveira.TCC.selectAspi.model.formacao;

import lombok.Data;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Data
public class FormacaoDTO {
    private String instituicao;
    private String curso;
    private String nivel;
    private String inicio;
    private String fim;


    public FormacaoDTO(String instituicao, String curso, Nivel nivel, LocalDate inicio, LocalDate fim) {
        this.instituicao = instituicao;
        this.curso = curso;
        this.nivel = nivel.getTexto();
        this.inicio = formatDate(inicio);
        this.fim = formatDate(fim);
    }

    private String formatDate(LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return formatter.format(data);
    }
}
