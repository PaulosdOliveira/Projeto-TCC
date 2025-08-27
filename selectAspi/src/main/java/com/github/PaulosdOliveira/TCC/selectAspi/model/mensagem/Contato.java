package com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem;


import lombok.Data;

import java.util.UUID;


@Data
public class Contato {
    private final String id;
    private final String nome;
    private final String ultimaMensagem;
    private String urlFoto;

    public Contato(Long id, String nome, String ultimaMensagem) {
        String[] nomeDividido = nome.split(" ");
        this.id = id.toString();
        this.nome = nomeDividido[0] + " " + (nomeDividido.length > 1 ? nomeDividido[nomeDividido.length - 1] : "");
        this.ultimaMensagem = ultimaMensagem;
        this.urlFoto = "http://localhost:8080/candidato/foto/" + id;
    }

    public Contato(UUID id, String nome, String ultimaMensagem) {
        String[] nomeDividido = nome.split(" ");
        this.id = id.toString();
        this.nome = nomeDividido[0] + " " + (nomeDividido.length > 1 ? nomeDividido[nomeDividido.length - 1] : "");
        this.ultimaMensagem = ultimaMensagem;
        this.urlFoto = "http://localhost:8080/empresa/foto/" + id;
    }


}
