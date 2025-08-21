package com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem;


import lombok.Data;
import java.util.UUID;


@Data
public class Contato {
    private final String id;
    private final String nome;
    private final String ultimaMensagem;

    public Contato(Long id, String nome, String ultimaMensagem) {
        this.id = id.toString();
        this.nome = nome;
        this.ultimaMensagem = ultimaMensagem;
    }

    public Contato(UUID id, String nome, String ultimaMensagem) {
        this.id = id.toString();
        this.nome = nome;
        this.ultimaMensagem = ultimaMensagem;
    }
}
