package github.fdevos.mscreditappraiser.infra.client;

import github.fdevos.mscreditappraiser.domain.model.Card;
import github.fdevos.mscreditappraiser.domain.model.UserCard;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mscards", path = "/cartoes")
public interface CardsResourceClient {


    @GetMapping(params = "cpf")
    ResponseEntity<List<UserCard>> getCardsByClient(@RequestParam("cpf") String cpf);

    @GetMapping(params = "renda")
    ResponseEntity<List<Card>> getCardIncomeEqualsOrLessThen(@RequestParam("renda") Long income);
}
