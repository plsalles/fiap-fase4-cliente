package br.com.fiap.cliente.usecase;

import br.com.fiap.cliente.controller.ClienteControllerMapper;
import br.com.fiap.cliente.controller.ClienteDTO;
import br.com.fiap.cliente.gateway.ClienteGateway;
import br.com.fiap.cliente.usecase.validacoes.ValidaClienteRule;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@Service
public class ClienteUseCase {

    private final ClienteGateway clienteGateway;
    private final ClienteControllerMapper mapper;
    private final List<ValidaClienteRule> validaClienteRule;

    public Collection<ClienteDTO> findAllUsers() {
        return clienteGateway.findAllCliente().stream().map(mapper::toClienteDTO).toList();
    }

    public ClienteDTO findClienteById(Long id) {
        validarCliente(id);
        return mapper.toClienteDTO(clienteGateway.findById(id));
    }
    
    public ClienteDTO criarCliente(ClienteDTO clienteDTO) {
        val clienteDomain = mapper.toClienteDomain(clienteDTO);
        val clienteEntity = mapper.toClienteEntity(clienteDomain);
        return mapper.toClienteDTO(clienteGateway.save(clienteEntity));
    }

    public ClienteDTO updateCliente(Long id, ClienteDTO clienteDTO) {
        validarCliente(id);
        val clienteDomain = mapper.toClienteDomain(findClienteById(id));
        val clienteDomainNew = mapper.toClienteDomain(clienteDTO);

        clienteDomain.setNome(clienteDomainNew.getNome());
        clienteDomain.setEmail(clienteDomainNew.getEmail());
        return mapper.toClienteDTO(clienteGateway.save(mapper.toClienteEntity(clienteDomain)));
    }

    public void deletaCliente(Long id) {
        validarCliente(id);
        clienteGateway.deleteById(id);
    }

    private void validarCliente(Long id) {
        validaClienteRule.forEach(j -> j.validarCliente(id));
    }

}
