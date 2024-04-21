package com.softexpert.projetotesteapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class PaymentBody {

    private String email;
    private String telefone;
    private String nome;
    private String sobrenome;
    private String endereco;
    private String quantidade;

    public static class Address {
        private String rua;
        private String cidade;
        private String estado;
        private String cep;
    }

}