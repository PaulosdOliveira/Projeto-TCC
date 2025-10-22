package com.github.PaulosdOliveira.TCC.selectAspi.exception.ex;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import com.github.PaulosdOliveira.TCC.selectAspi.exception.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import static com.github.PaulosdOliveira.TCC.selectAspi.application.UtilsService.getMap;

import java.util.HashMap;
import java.util.Map;

@Hidden
@RestControllerAdvice()
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
        return getMap("Erro", e.getMessage());
    }


    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(EmailDuplicadoException.class)
    public Map<String, Object> handlerEmailDuplicadoException(EmailDuplicadoException e) {
        return getMap("Erro",e.getMessage());
    }


    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(CnpjDuplicadoException.class)
    public Map<String, Object> handlerCnpjDuplicadoException(CnpjDuplicadoException e) {
        return getMap("Erro",e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(VagaNaoEncontradaException.class)
    public Map<String, Object> handlerVagaNaoEncontradaException(VagaNaoEncontradaException e) {
        System.out.println(e.getMessage());
        return getMap("Erro",e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public Map<String, Object> handlerUsuarioNaoEncontradoException(UsuarioNaoEncontradoException e) {
        System.out.println(e.getMessage());
        return getMap("Erro",e.getMessage());
    }

    @ResponseStatus(HttpStatus.GONE)
    @ExceptionHandler(VagaEncerradaException.class)
    public Map<String, Object> handlerVagaEncerradaException(VagaEncerradaException e) {
        return getMap("Erro",e.getMessage());
    }


    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(CandidaturaJaCadastradaException.class)
    public Map<String, Object> handlerCandidaturaJaCadastradaException(CandidaturaJaCadastradaException e) {
        return getMap("Erro",e.getMessage());
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UsernameNotFoundException.class)
    public Map<String, Object> handlerUsernameNotFoundException(UsernameNotFoundException e) {
        return getMap("Erro",e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(RazaoSocialDuplicadoException.class)
    public Map<String, Object> handlerRazaoSocialDuplicadoException(RazaoSocialDuplicadoException e) {
        return getMap("Erro",e.getMessage());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(IdadeBaixaException.class)
    public Map<String, Object> handlerIdadeBaixaException(IdadeBaixaException e) {
        return getMap("Erro",e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public Map<String, Object> handlerRuntimeException(RuntimeException e) {
        System.out.println(e);
        return getMap("Erro",e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HandlerMethodValidationException.class)
    public Map<String, Object> handlerMethodValidationException(HandlerMethodValidationException e) {
        System.out.println(e.getMessage());
        return getMap("Erro",e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CidadeInvalidaException.class)
    public Map<String, Object> handlerCidadeInvalidaException(CidadeInvalidaException e) {
        System.out.println(e.getMessage());
        return getMap("Erro",e.getMessage());
    }




}
