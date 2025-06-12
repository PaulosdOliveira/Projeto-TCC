package com.github.PaulosdOliveira.TCC.selectAspi.application.ex;

import org.springframework.web.bind.MethodArgumentNotValidException;
import com.github.PaulosdOliveira.TCC.selectAspi.exception.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
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
    public String handlerCPFDuplicadoExcepton(CPFDuplicadoExcepton e) {
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CepInvalidoException.class)
    public String handlerCepInvalidoException(CepInvalidoException e) {
        return e.getMessage();
    }


    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(EmailDuplicadoException.class)
    public String handlerEmailDuplicadoException(EmailDuplicadoException e) {
        return e.getMessage();
    }


    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(CnpjDuplicadoException.class)
    public String handlerCnpjDuplicadoException(CnpjDuplicadoException e) {
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(VagaNaoEncontradaException.class)
    public String handlerVagaNaoEncontradaException(VagaNaoEncontradaException e) {
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.GONE)
    @ExceptionHandler(VagaEncerradaException.class)
    public String handlerVagaEncerradaException(VagaEncerradaException e) {
        return e.getMessage();
    }


    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(CandidaturaJaCadastradaException.class)
    public String handlerCandidaturaJaCadastradaException(CandidaturaJaCadastradaException e) {
        return e.getMessage();
    }

}
