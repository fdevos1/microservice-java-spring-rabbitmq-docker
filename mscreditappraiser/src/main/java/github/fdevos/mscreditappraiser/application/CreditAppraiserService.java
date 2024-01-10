package github.fdevos.mscreditappraiser.application;

import feign.FeignException;
import github.fdevos.mscreditappraiser.application.ex.MicroserviceCommunicationErrorException;
import github.fdevos.mscreditappraiser.application.ex.UserDataNotFoundException;
import github.fdevos.mscreditappraiser.domain.model.*;
import github.fdevos.mscreditappraiser.infra.client.CardsResourceClient;
import github.fdevos.mscreditappraiser.infra.client.UserResourceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

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

    public UserAvaliationReturn makeAvaliation(String cpf, long income) throws UserDataNotFoundException, MicroserviceCommunicationErrorException {
        try {


            ResponseEntity<UserData> userData = userClient.userData(cpf);
            ResponseEntity<List<Card>> userCards = cardsClient.getCardIncomeEqualsOrLessThen(income);

            List<Card> cards = userCards.getBody();
            var approvedCardList = cards.stream().map(card -> {
                UserData userPersonalData = userData.getBody();


                BigDecimal initialLimit = card.getInitialLimit();
                BigDecimal ageBD = BigDecimal.valueOf(userPersonalData.getAge());

                var fator = ageBD.divide(BigDecimal.valueOf(10));
                BigDecimal availableLimit = fator.multiply(initialLimit);

                ApprovedCard approvedCard = new ApprovedCard();
                approvedCard.setCard(card.getName());
                approvedCard.setFlag(card.getFlag());
                approvedCard.setAvailableLimit(availableLimit);

                return approvedCard;
            }).collect(Collectors.toList());

            return new UserAvaliationReturn(approvedCardList);

        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new UserDataNotFoundException();
            }
            throw new MicroserviceCommunicationErrorException(e.getMessage(), status);
        }

    }


}
