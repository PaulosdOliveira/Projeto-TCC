package com.github.PaulosdOliveira.TCC.selectAspi.application;

import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.CandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("utils")
public class UtilsController {

    @Autowired
    private CandidatoRepository repository;

    @GetMapping("/estados")
    public List<String> buscarEstados(){
        return repository.buscarEstados();
    }

    @GetMapping("/cidades/{uf}")
    public List<String> buscarCiiades(@PathVariable String uf){
        return repository.buscarCidades(uf);
    }

}
