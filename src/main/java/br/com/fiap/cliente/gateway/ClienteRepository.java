package br.com.fiap.cliente.gateway;

import br.com.fiap.cliente.domain.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity> {
}
