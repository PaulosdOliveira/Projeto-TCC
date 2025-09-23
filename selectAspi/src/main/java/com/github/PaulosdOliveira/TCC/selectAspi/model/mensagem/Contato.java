package com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem;


import lombok.Data;

import java.util.UUID;


@Data
public class Contato {
    private final String id;
    private final String nome;
    private final String ultimaMensagem;
    private String urlFoto = "http://localhost:8080";
    private final Long naoVizualizadas;


    // CASO O CONTATO SEJA UM  CANDIDATO
    public Contato(Long id, String nome, String ultimaMensagem, Long naoVizualizadas) {
        String[] nomeDividido = nome.split(" ");
        this.id = id.toString();
        this.nome = nomeDividido[0] + " " + (nomeDividido.length > 1 ? nomeDividido[nomeDividido.length - 1] : "");
        this.ultimaMensagem = ultimaMensagem;
        this.urlFoto += "/candidato/foto/" + id;
        this.naoVizualizadas = naoVizualizadas;
    }


    // CASO O CONTATO SEJA UMA EMPRESA
    public Contato(UUID id, String nome, String ultimaMensagem, Long naoVizualizadas) {
        this.id = id.toString();
        System.out.println("Nome: " + nome);
        String[] nomeDividido = nome.split(" ");
        this.nome = nomeDividido[0] + " " + (nomeDividido.length > 1 ? nomeDividido[nomeDividido.length - 1] : "");
        this.ultimaMensagem = ultimaMensagem;
        this.urlFoto += "/empresa/foto/" + id;
        this.naoVizualizadas = naoVizualizadas;
    }

}
