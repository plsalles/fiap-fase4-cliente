package br.com.fiap.cliente.controller;

import br.com.fiap.cliente.domain.Cliente;
import br.com.fiap.cliente.domain.ClienteEntity;

import java.util.UUID;

public class ClienteControllerMapper {

    public Cliente toClienteDomain(ClienteDTO clienteDTO) {
        return new Cliente(
                clienteDTO.name(),
                clienteDTO.email(),
                clienteDTO.cpf());
    }

    public ClienteEntity toUserEntity(Cliente cliente) {
        return new ClienteEntity(
                cliente.getName(),
                cliente.getEmail(),
                cliente.getCpf());
    }

    public ClienteEntity toClienteEntity(UUID id, Cliente cliente) {
        return new ClienteEntity(
                id,
                cliente.getName(),
                cliente.getEmail(),
                cliente.getCpf());
    }

    public ClienteDTO toUserDTO(ClienteEntity clienteEntity) {
        return new ClienteDTO(
                clienteEntity.getId(),
                clienteEntity.getName(),
                clienteEntity.getEmail(),
                clienteEntity.getCpf()
        );
    }
}
