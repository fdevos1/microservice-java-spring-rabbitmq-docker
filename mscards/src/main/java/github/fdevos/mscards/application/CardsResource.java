package github.fdevos.mscards.application;

import github.fdevos.mscards.application.representation.CardSaveRequest;
import github.fdevos.mscards.application.representation.CardsByClientResponse;
import github.fdevos.mscards.domain.Card;
import github.fdevos.mscards.domain.ClientCard;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cartoes")
@RequiredArgsConstructor
public class CardsResource {

    private final CardService cardService;
    private final ClientCardService clientService;



    @GetMapping
    public String status() {
        return "Ok";
    }

    @PostMapping
    public ResponseEntity register(@RequestBody CardSaveRequest request) {
        Card card = request.toModel();
        cardService.save(card);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @GetMapping(params = "renda")
    public ResponseEntity<List<Card>> getCardIncomeEqualsOrLessThen(@RequestParam("renda") Long income) {
        List<Card> list = cardService.getCardIncomeEqualsOrLessThen(income);
        return ResponseEntity.ok(list);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CardsByClientResponse>> getCardsByClient(@RequestParam("cpf") String cpf) {
        List<ClientCard> list = clientService.listCardsByCpf(cpf);
        List<CardsByClientResponse> resultList = list.stream().map(CardsByClientResponse::fromModel).collect(Collectors.toList());

        return ResponseEntity.ok(resultList);
    }
}
