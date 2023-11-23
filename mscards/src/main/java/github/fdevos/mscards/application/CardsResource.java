package github.fdevos.mscards.application;

import github.fdevos.mscards.application.representation.CardSaveRequest;
import github.fdevos.mscards.domain.Card;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cartoes")
@RequiredArgsConstructor
public class CardsResource {

    private final CardService service;



    @GetMapping
    public String status() {
        return "Ok";
    }

    @PostMapping
    public ResponseEntity register(@RequestBody CardSaveRequest request) {
        Card card = request.toModel();
        service.save(card);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @GetMapping(params = "renda")
    public ResponseEntity<List<Card>> getCardIncomeLessThenEquals(@RequestParam("renda") Long income) {
        List<Card> list = service.getCardWithIncomeLessThanEquals(income);
        return ResponseEntity.ok(list);
    }
}
