package br.com.fiap.cliente.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.time.LocalDate;
import java.util.UUID;

public record ClienteDTO(@JsonIgnore @ReadOnlyProperty UUID id,
        String nome,
        String email,
        String telefone,
        String endereco,
        LocalDate dataCadastro) {

    public ClienteDTO(UUID id, String name, String email, String telefone, String endereco, LocalDate dataCadastro) {
        this(null, name, email, telefone, endereco, dataCadastro);
    }
}
