package com.softexpert.projetotesteapi.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class Cliente {

    private Long id;
    private String nome;
    private String email;
    private List<Itens> itensList;
    private BigDecimal valorAPagar;

    public BigDecimal calcularValorPedido() {
        return Optional.ofNullable(itensList)
                .orElse(List.of())
                .stream()
                .map(Itens::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}