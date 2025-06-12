package com.github.PaulosdOliveira.TCC.selectAspi.application;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UtilsService {


    public ResponseEntity<byte[]> renderizarFoto(byte[] foto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentLength(foto.length);
        return new ResponseEntity<>(foto, headers, HttpStatus.OK);
    }
}
