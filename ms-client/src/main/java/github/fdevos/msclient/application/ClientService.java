package github.fdevos.msclient.application;

import github.fdevos.msclient.domain.Client;
import github.fdevos.msclient.infra.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository repository;



    @Transactional
    public Client save(Client cliente) {
        return repository.save(cliente);
    }

    public Optional<Client> getByCPF(String cpf) {
        return repository.findByCpf(cpf);
    }

}
