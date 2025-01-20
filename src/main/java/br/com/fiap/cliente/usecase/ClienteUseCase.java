package br.com.fiap.cliente.usecase;

import br.com.fiap.cliente.controller.ClienteControllerMapper;
import br.com.fiap.cliente.controller.ClienteDTO;
import br.com.fiap.cliente.gateway.ClienteGateway;
import lombok.AllArgsConstructor;
import lombok.val;

import java.util.Collection;
import java.util.UUID;

@AllArgsConstructor
public class ClienteUseCase {
    private final ClienteGateway clienteGateway;
    private final ClienteControllerMapper mapper;

    public Collection<ClienteDTO> findAllUsers() {
        return clienteGateway.findAll().stream().map(mapper::toClienteDTO).toList();
    }

    public ClienteDTO findClienteById(UUID id) {
        return mapper.toClienteDTO(clienteGateway.findById(id));
    }

    public ClienteDTO criarCliente(ClienteDTO clienteDTO) {
        val clienteDomain = mapper.toClienteDomain(clienteDTO);
        val clienteEntity = mapper.toClienteEntity(clienteDomain);
        return mapper.toClienteDTO(clienteGateway.save(clienteEntity));
    }

    public ClienteDTO updateCliente(UUID id, ClienteDTO clienteDTO) {
        val clienteDomain = mapper.toClienteDomain(findClienteById(id));
        val clienteDomainNew = mapper.toClienteDomain(clienteDTO);
        clienteDomain.setName(clienteDomainNew.getName());
        clienteDomain.setEmail(clienteDomainNew.getEmail());
        return mapper.toClienteDTO(clienteGateway.save(mapper.toClienteEntity(id, clienteDomain)));
    }

    public void deletaCliente(UUID id) {
        clienteGateway.deleteById(id);
    }

    }
}
