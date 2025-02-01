package br.com.fiap.cliente.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.time.LocalDate;

public record ClienteDTO(@JsonIgnore @ReadOnlyProperty Long id,
                         String nome,
                         String email,
                         String telefone,
                         String endereco,
                         LocalDate dataCadastro) {

    public ClienteDTO(String name, String email, String telefone, String endereco, LocalDate dataCadastro) {
        this(null, name, email, telefone, endereco, dataCadastro);
    }
}
