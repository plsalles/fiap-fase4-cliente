package br.com.fiap.cliente.controller;

import br.com.fiap.cliente.domain.Cliente;
import br.com.fiap.cliente.gateway.database.jpa.entity.ClienteEntity;

import java.time.LocalDate;
import java.util.UUID;

public class ClienteControllerMapper {

    public Cliente toClienteDomain(ClienteDTO clienteDTO) {
        return new Cliente(
                clienteDTO.nome(),
                clienteDTO.email(),
                clienteDTO.telefone(),
                clienteDTO.endereco(),
                clienteDTO.dataCadastro());
    }

    public Cliente toClienteDomain(ClienteEntity clienteEntity) {
        return new Cliente(
                clienteEntity.getNome(),
                clienteEntity.getEmail(),
                clienteEntity.getTelefone(),
                clienteEntity.getEndereco(),
                clienteEntity.getDataCadastro());
    }

    public ClienteEntity toClienteEntity(Cliente cliente) {
        return new ClienteEntity(
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getTelefone(),
                cliente.getEndereco(),
                cliente.getDataCadastro());
    }

    public ClienteDTO toClienteDTO(ClienteEntity clienteEntity) {
        return new ClienteDTO(
                clienteEntity.getNome(),
                clienteEntity.getEmail(),
                clienteEntity.getTelefone(),
                clienteEntity.getEndereco(),
                clienteEntity.getDataCadastro()
        );
    }
}
