package com.github.PaulosdOliveira.TCC.selectAspi.application.ex;

import com.github.PaulosdOliveira.TCC.selectAspi.exception.CPFDuplicadoExcepton;
import com.github.PaulosdOliveira.TCC.selectAspi.exception.CepInvalidoException;
import com.github.PaulosdOliveira.TCC.selectAspi.exception.EmailDuplicadoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerException {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, Object> map = new HashMap<>();
        e.getFieldErrors().forEach(erros -> {
            map.put(erros.getField(), erros.getDefaultMessage());
        });
        return map;
    }


    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(CPFDuplicadoExcepton.class)
    public String handlerCPFDuplicadoExcepton(CPFDuplicadoExcepton e){
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CepInvalidoException.class)
    public String handlerCepInvalidoException(CepInvalidoException e){
        return e.getMessage();
    }


    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(EmailDuplicadoException.class)
    public String handlerEmailDuplicadoException(EmailDuplicadoException e){
        return e.getMessage();
    }






}
