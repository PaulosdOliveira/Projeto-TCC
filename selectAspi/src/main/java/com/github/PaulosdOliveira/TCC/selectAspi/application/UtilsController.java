package com.github.PaulosdOliveira.TCC.selectAspi.application;

import com.github.PaulosdOliveira.TCC.selectAspi.model.localizacao.CidadeDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.localizacao.Estado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("utils")
public class UtilsController {

    @Autowired
    private UtilsService service;

    @GetMapping("/estados")
    public List<Estado> buscarEstados() {
        return service.buscarEstados();
    }

    @GetMapping("/cidades/{idEstado}")
    public List<CidadeDTO> buscarCidadesDeEstado(@PathVariable int idEstado) {
        return service.buscarCidadesDeEstado(idEstado);
    }

}
