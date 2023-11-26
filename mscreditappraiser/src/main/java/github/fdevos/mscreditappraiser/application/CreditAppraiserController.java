package github.fdevos.mscreditappraiser.application;

import github.fdevos.mscreditappraiser.domain.model.UserSituation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("avaliacoes-credito")
@RequiredArgsConstructor
public class CreditAppraiserController {

    private final CreditAppraiserService creditAppraiserService;

    @GetMapping
    public String status() {
        return "OK!";
    }


    @GetMapping(value = "situacao-cliente", params = "cpf")
    public ResponseEntity<UserSituation> consultUserSituation(@RequestParam("cpf") String cpf) {
        UserSituation userSituation = creditAppraiserService.findUserSituation(cpf);

        return ResponseEntity.ok(userSituation);
    }
}
