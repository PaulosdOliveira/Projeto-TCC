package com.github.PaulosdOliveira.TCC.selectAspi.application.ex;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public Map<String, Object> handlerCPFDuplicadoExcepton(CPFDuplicadoExcepton e) {
        return getMap(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CepInvalidoException.class)
    public Map<String, Object> handlerCepInvalidoException(CepInvalidoException e) {
        return getMap(e.getMessage());
    }


    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(EmailDuplicadoException.class)
    public Map<String, Object> handlerEmailDuplicadoException(EmailDuplicadoException e) {
        return getMap(e.getMessage());
    }


    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(CnpjDuplicadoException.class)
    public Map<String, Object> handlerCnpjDuplicadoException(CnpjDuplicadoException e) {
        return getMap(e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(VagaNaoEncontradaException.class)
    public Map<String, Object> handlerVagaNaoEncontradaException(VagaNaoEncontradaException e) {
        return getMap(e.getMessage());
    }

    @ResponseStatus(HttpStatus.GONE)
    @ExceptionHandler(VagaEncerradaException.class)
    public Map<String, Object> handlerVagaEncerradaException(VagaEncerradaException e) {
        return getMap(e.getMessage());
    }


    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(CandidaturaJaCadastradaException.class)
    public Map<String, Object> handlerCandidaturaJaCadastradaException(CandidaturaJaCadastradaException e) {
        return getMap(e.getMessage());
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UsernameNotFoundException.class)
    public Map<String, Object> handlerUsernameNotFoundException(UsernameNotFoundException e) {
        return getMap(e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(RazaoSocialDuplicadoException.class)
    public Map<String, Object> handlerRazaoSocialDuplicadoException(RazaoSocialDuplicadoException e) {
        return getMap(e.getMessage());
    }


    // Padroniizando erros
    private Map<String, Object> getMap(String erro) {
        Map<String, Object> map = new HashMap<>();
        map.put("erro", erro);
        return map;
    }

}
