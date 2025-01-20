package br.com.fiap.cliente.gateway;

import br.com.fiap.cliente.domain.ClienteEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.Collection;
import java.util.UUID;

@AllArgsConstructor
public class ClienteGateway {
    private final ClienteRepository clienteRepository;

    public Collection<ClienteEntity> findAll() {
        return clienteRepository.findAll();
    }

    public ClienteEntity findById(UUID id){
        return clienteRepository.findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException("Cliente " + id + " não encontrado."));
    }

    public ClienteEntity save(ClienteEntity clienteEntity) {
        return (ClienteEntity) clienteRepository.save(clienteEntity);
    }

    public void deleteById(UUID id) {
        clienteRepository.deleteById(id);
    }
}
