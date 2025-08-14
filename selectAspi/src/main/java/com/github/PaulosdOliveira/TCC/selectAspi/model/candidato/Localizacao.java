package com.github.PaulosdOliveira.TCC.selectAspi.model.candidato;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Deprecated
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Localizacao {
    private String uf;
    private String localidade;


    @Override
    public String toString() {
        return " Estado: " + uf + "\n Cidade: " + localidade;
    }
}
