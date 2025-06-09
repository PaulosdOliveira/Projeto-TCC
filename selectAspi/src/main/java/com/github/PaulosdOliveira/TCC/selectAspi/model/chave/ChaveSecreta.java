package com.github.PaulosdOliveira.TCC.selectAspi.model.chave;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Arrays;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class ChaveSecreta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private byte[] encoded;

    @Column(nullable = false, length = 20)
    private String algoritimo;

    private LocalDateTime  dataCriacao;


    @Override
    public String toString() {
        return "ChaveSecreta{" +
                "id=" + id +
                ", encoded=" + Arrays.toString(encoded) +
                ", algoritimo='" + algoritimo + '\'' +
                ", dataCriacao=" + dataCriacao +
                '}';
    }
}
