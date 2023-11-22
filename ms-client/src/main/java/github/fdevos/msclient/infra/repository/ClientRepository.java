package github.fdevos.msclient.infra.repository;

import github.fdevos.msclient.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Long> {
    Optional<Client> findByCpf(String cpf);
}
