package com.github.PaulosdOliveira.TCC.selectAspi.model.vaga;

import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Sexo;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CardVagaDTO {

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

    public CardVagaDTO(String nome_empresa, String titulo, String cidade, String estado, Float salario, String modelo, String tipo_contrato, String nivel, boolean exclusivo_pcd, Sexo sexoExclusivo, LocalDateTime dataEnvio) {
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
        this.periodo = periodo(dataEnvio);
    }


    private String periodo(LocalDateTime dataEnvio) {
        var dataAtual = LocalDateTime.now();
        String periodo = "Há ";
        if (dataAtual.getYear() != dataEnvio.getYear()) {
            periodo += (dataAtual.getYear() - dataEnvio.getYear()) + " Anos";
        } else if (dataAtual.getMonth() != dataEnvio.getMonth()) {
            var meses = dataAtual.getMonthValue() - dataEnvio.getMonthValue();
            periodo += meses + (meses == 1 ? " Mês" : " Meses");
        } else if (dataAtual.getDayOfMonth() != dataEnvio.getDayOfMonth()) {
            var dias = dataAtual.getDayOfMonth() - dataEnvio.getDayOfMonth();
            periodo += dias + (dias == 1 ? " Dia" : " Dias");
        } else if (dataAtual.getHour() != dataEnvio.getHour()) {
            var horas = dataAtual.getHour() - dataEnvio.getHour();
            periodo += horas + (horas == 1 ? " Hora" : " Horas");
        } else{
            var minutos = dataAtual.getMinute() - dataEnvio.getMinute();
            periodo += minutos + (minutos == 1 ? " Minuto" : " Minutos");
        }

        return periodo;
    }
}
