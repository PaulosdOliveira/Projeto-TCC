package com.github.PaulosdOliveira.TCC.selectAspi.model.candidato;

public enum Sexo {
    FEMININO("Feminino"),
    MASCULINO("Masculino"),
    TODOS ("");

    private final String descricao;

    Sexo(String descicao){
        this.descricao = descicao;
    }


    public String getDescricao(){
        return this.descricao;
    }
}
