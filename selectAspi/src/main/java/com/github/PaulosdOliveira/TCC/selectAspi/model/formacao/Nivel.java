package com.github.PaulosdOliveira.TCC.selectAspi.model.formacao;

import lombok.Getter;

@Getter
public enum Nivel {
    TECNICO("Técnico"),
    TECNOLOGO("Tecnólogo"),
    GRADUACAO("Graduação"),
    POS_GRADUACAO("Pos graduação"),
    MESTRADO("Mestrado"),
    DOUTORADO("Doutorado");

    private final String texto;


    Nivel (String texto){
        this.texto = texto;
    }


}
