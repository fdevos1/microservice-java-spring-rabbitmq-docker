package github.fdevos.mscards.application;

import github.fdevos.mscards.domain.ClientCard;
import github.fdevos.mscards.infra.repository.ClientCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientCardService {

    private final ClientCardRepository repository;

    public List<ClientCard> listCardsByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }
}
