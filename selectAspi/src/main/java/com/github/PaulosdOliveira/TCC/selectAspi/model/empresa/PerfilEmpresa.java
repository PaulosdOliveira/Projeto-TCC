package com.github.PaulosdOliveira.TCC.selectAspi.model.empresa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PerfilEmpresa {
    private UUID id;
    private String nome;
    private String descricao;
}
