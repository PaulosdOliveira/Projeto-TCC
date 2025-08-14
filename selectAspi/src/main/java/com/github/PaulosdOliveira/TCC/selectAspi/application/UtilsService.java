package com.github.PaulosdOliveira.TCC.selectAspi.application;

import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.CidadeRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.EstadoRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.model.localizacao.CidadeDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.localizacao.Estado;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UtilsService {


    private final EstadoRepository estadoRepository;
    private final CidadeRepository cidadeRepository;


    public List<Estado> buscarEstados(){
        return estadoRepository.findAll();
    }

    public List<CidadeDTO> buscarCidadesDeEstado(int idEstado){
        return cidadeRepository.buscarCidadesDeEstado(idEstado);
    }


    // Função responsável por retornar fotos para os endPoints de renderização de foto
    public ResponseEntity<byte[]> renderizarFoto(byte[] foto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentLength(foto.length);
        return new ResponseEntity<>(foto, headers, HttpStatus.OK);
    }

    // VERIFICANDO O TEMPO DECORRIDO DESDE A POSTAGEM DA VAGA
    public static String getTempoDecorrido(LocalDateTime dataEnvio) {
        var dataAtual = LocalDateTime.now();
        long anos = ChronoUnit.YEARS.between(dataEnvio, dataAtual);
        if (anos > 0) return "Há " + anos + " Anos";
        long meses = ChronoUnit.MONTHS.between(dataEnvio, dataAtual);
        if (meses > 0) return "Há " + meses + " Meses";
        long dias = ChronoUnit.DAYS.between(dataEnvio, dataAtual);
        if (dias > 0) return "Há " + dias + (dias > 1 ? " Dias" : " Dia");
        long horas = ChronoUnit.HOURS.between(dataEnvio, dataAtual);
        if (horas > 0) return "Há " + horas + " Horas";
        long minutos = ChronoUnit.MINUTES.between(dataEnvio, dataAtual);
        if (minutos > 0) return "Há " + minutos + " Minutos";
        return "Agora";
    }
}
