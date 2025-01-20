package br.com.fiap.cliente.usecase;

import br.com.fiap.cliente.controller.ClienteDTO;
import br.com.fiap.cliente.gateway.ClienteGateway;
import lombok.AllArgsConstructor;
import lombok.val;

@AllArgsConstructor
public class ClienteUseCase {
    private final ClienteGateway clienteGateway;
    private final ClienteControllerMapper mapper;

    public Collection<ClienteDTO> findAllUsers() {
        return clienteGateway.findAll().stream().map(mapper::toClienteDTO).toList();
    }

    public ClienteDTO findUserById(Long id) {
        return mapper.toClienteDTO(clienteGateway.findById(id));
    }

    public ClienteDTO criarCliente(ClienteDTO clienteDTO) {
        val userDomain = mapper.toClienteDomain(clienteDTO);
        val userEntity = mapper.toClienteEntity(clienteDomain);
        return mapper.toClienteDTO(clienteGateway.save(clienteEntity));
    }

    public ClienteDTO updateCliente(Long id, ClienteDTO clienteDTO) {
        val userDomain = mapper.toUserDomain(findUserById(id));
        val userDomainNew = mapper.toUserDomain(userDTO);
        clienteDomain.setName(clienteDomainNew.getName());
        clienteDomain.setEmail(clienteDomainNew.getEmail());
        return mapper.toClienteDTO(clienteGateway.save(mapper.toClienteEntity(id, clienteDomain)));
    }

    public void deleteCliente(Long id) {
        clienteGateway.deleteById(id);
    }

    }
}
