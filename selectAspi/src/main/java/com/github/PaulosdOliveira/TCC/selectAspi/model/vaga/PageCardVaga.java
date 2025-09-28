package com.github.PaulosdOliveira.TCC.selectAspi.model.vaga;

import lombok.Data;

import java.util.List;

@Data
public class PageCardVaga {
    private final List<CardVagaDTO> vagas;
    private final int pageNumber;
    private final long totalPages;
}
