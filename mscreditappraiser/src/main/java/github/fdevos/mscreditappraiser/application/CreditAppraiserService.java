package github.fdevos.mscreditappraiser.application;

import feign.FeignException;
import github.fdevos.mscreditappraiser.application.ex.MicroserviceCommunicationErrorException;
import github.fdevos.mscreditappraiser.application.ex.UserDataNotFoundException;
import github.fdevos.mscreditappraiser.domain.model.UserCard;
import github.fdevos.mscreditappraiser.domain.model.UserData;
import github.fdevos.mscreditappraiser.domain.model.UserSituation;
import github.fdevos.mscreditappraiser.infra.client.CardsResourceClient;
import github.fdevos.mscreditappraiser.infra.client.UserResourceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditAppraiserService {

    private final UserResourceClient userClient;
    private final CardsResourceClient cardsClient;

    public UserSituation findUserSituation(String cpf) throws UserDataNotFoundException, MicroserviceCommunicationErrorException {
        try {


            ResponseEntity<UserData> userData = userClient.userData(cpf);
            ResponseEntity<List<UserCard>> cardsResponse = cardsClient.getCardsByClient(cpf);


            return UserSituation.builder().user(userData.getBody()).cards(cardsResponse.getBody()).build();
        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new UserDataNotFoundException();
            }
            throw new MicroserviceCommunicationErrorException(e.getMessage(), status);
        }

    }

}
