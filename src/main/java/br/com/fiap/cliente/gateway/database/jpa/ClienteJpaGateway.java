package br.com.fiap.cliente.gateway.database.jpa;

import br.com.fiap.cliente.exception.NotFoundException;
import br.com.fiap.cliente.gateway.ClienteGateway;
import br.com.fiap.cliente.gateway.database.jpa.entity.ClienteEntity;
import br.com.fiap.cliente.gateway.database.jpa.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;

@AllArgsConstructor
@Component
public class ClienteJpaGateway implements ClienteGateway {
    private final ClienteRepository clienteRepository;

    @Override
    public Collection<ClienteEntity> findAllCliente() {
        return clienteRepository.findAll();
    }

    @Override
    public ClienteEntity findById(Long id) {
       return clienteRepository.findById(id).orElseThrow(() -> new NotFoundException("Cliente " + id + " não encontrado."));
    }

    @Override
    public ClienteEntity save(ClienteEntity clienteEntity) {
        return clienteRepository.save(clienteEntity);
    }

    @Override
    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }

}
