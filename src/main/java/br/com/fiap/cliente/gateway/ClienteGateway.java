package br.com.fiap.cliente.gateway;

import br.com.fiap.cliente.domain.ClienteEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;

@AllArgsConstructor
public class ClienteGateway {
    private final ClienteRepository clienteRepository;

    public Collection<ClienteEntity> findAll() {
        return clienteRepository.findAll();
    }

    public ClienteEntity findById(Long id){
        return clienteRepository.findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException("Cliente " + id + " não encontrado."));
    }

    public ClienteEntity save(ClienteEntity userEntity) {
        return (ClienteEntity) clienteRepository.save(userEntity);
    }

    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }
}
