package com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum PerfilRemetente {
    EMPRESA("empresa"),
    CANDIDATO("candidato");


    private final String perfil;

    PerfilRemetente(String perfil) {
        this.perfil = perfil;
    }


    public static PerfilRemetente ofName(String valor){
        return Arrays.stream(values())
                .filter(perfil -> perfil.name().equalsIgnoreCase(valor))
                .findFirst()
                .orElse(null);
    }

}
