package com.github.PaulosdOliveira.TCC.selectAspi.application;

import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.CidadeRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.EstadoRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.model.localizacao.CidadeDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.localizacao.Estado;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import lombok.RequiredArgsConstructor;

import java.time.temporal.ChronoUnit;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.*;

@RequiredArgsConstructor
@Service
public class UtilsService {


    private final EstadoRepository estadoRepository;
    private final CidadeRepository cidadeRepository;


    public List<Estado> buscarEstados() {
        return estadoRepository.findAll();
    }

    public List<CidadeDTO> buscarCidadesDeEstado(int idEstado) {
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

    // VERIFICAR TEMPO DE EXPERIÊNCIA
    public static String getTempoDeExperiencia(LocalDate inicio, LocalDate fim) {
        String tempoExperiencia;
        long anos = ChronoUnit.YEARS.between(inicio, fim);
        long meses = ChronoUnit.MONTHS.between(inicio.plusYears(anos), fim);
        tempoExperiencia = anos > 0 ? anos + (anos > 1 ? " anos" : " ano") + (meses > 0 ? " e " : "") : "";
        tempoExperiencia += meses > 0 ? meses + (meses > 1 ? " meses" : " mês") : "";
        return tempoExperiencia;
    }


    public static Long getIdCandidatoLogado() {
        try {
            return Long.parseLong(
                    SecurityContextHolder.getContext().getAuthentication().getCredentials().toString()
            );
        } catch (Exception e) {
            System.out.println("Erro ao buscar id da empresa logada");
            return null;
        }
    }


    public static UUID getIdEmpresaLogada() {
        try {
            return UUID.fromString(
                    SecurityContextHolder.getContext().getAuthentication().getCredentials().toString()
            );
        } catch (Exception e) {
            System.out.println("Erro ao buscar id da empresa logada");
            return null;
        }
    }

    public static String getIdAuthString() {
        try {
            return SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        } catch (Exception e) {
            System.out.println("Erro ao buscar id do usuário logado");
            return null;
        }
    }


    // Padroniizando erros
    public static Map<String, Object> getMap(String label, String value) {
        Map<String, Object> map = new HashMap<>();
        map.put(label, value);
        return map;
    }

    // Padroniizando erros
    public static Map<String, Object> objectErro(String erro, String[][] matrizObject) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("erro", erro);
        for (int i = 0; i < matrizObject[0].length; i++) {
            map.put(matrizObject[0][i], matrizObject[1][i]);
        }
        return map;
    }


}
