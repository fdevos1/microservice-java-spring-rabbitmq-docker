package github.fdevos.mscreditappraiser.application;

import github.fdevos.mscreditappraiser.domain.model.UserData;
import github.fdevos.mscreditappraiser.domain.model.UserSituation;
import github.fdevos.mscreditappraiser.infra.client.UserResourceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditAppraiserService {

    private final UserResourceClient userClient;

    public UserSituation findUserSituation(String cpf) {
        ResponseEntity<UserData> userData = userClient.userData(cpf);

        return UserSituation.builder().user(userData.getBody()).build();
    }

}
