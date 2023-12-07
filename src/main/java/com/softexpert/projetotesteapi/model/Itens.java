package com.softexpert.projetotesteapi.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Itens {

    private String nomeProduto;
    private String clienteSolicitante;
    private BigDecimal valor;


}