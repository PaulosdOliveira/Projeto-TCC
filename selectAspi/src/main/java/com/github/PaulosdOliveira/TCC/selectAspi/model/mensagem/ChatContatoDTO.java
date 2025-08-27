package com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@Data
public class ChatContatoDTO {
    private String nome;
    private String urlFoto;


    public ChatContatoDTO(String nome, Long idCandidato) {
        this.nome = nome;
        this.urlFoto = "http://localhost:8080/candidato/foto/" + idCandidato;
    }


    public ChatContatoDTO(String nome, UUID idEmpresa) {
        this.nome = nome;
        this.urlFoto = "http://localhost:8080/empresa/foto/" + idEmpresa;
    }
}
