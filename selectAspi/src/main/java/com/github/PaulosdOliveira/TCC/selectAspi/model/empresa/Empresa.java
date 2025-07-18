package com.github.PaulosdOliveira.TCC.selectAspi.model.empresa;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

@NoArgsConstructor
@Data
@Entity
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true, length = 20)
    private String cnpj;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, length = 180, unique = true)
    private String email;

    @Column(nullable = false, length = 300)
    private String senha;

    @Column()
    private String descricao;

    @Column()
    private byte[] foto;

    @Column()
    private byte[] capa;

    public Empresa(CadastroEmpresaDTO dadosCadastrais){
        BeanUtils.copyProperties(dadosCadastrais, this);
    }

    public Empresa(String id) {
        this.id = UUID.fromString(id);
    }
}
