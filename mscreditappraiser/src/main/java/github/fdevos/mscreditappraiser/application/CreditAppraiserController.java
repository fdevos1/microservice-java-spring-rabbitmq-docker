package github.fdevos.mscreditappraiser.application;

import github.fdevos.mscreditappraiser.application.ex.MicroserviceCommunicationErrorException;
import github.fdevos.mscreditappraiser.application.ex.UserDataNotFoundException;
import github.fdevos.mscreditappraiser.domain.model.AvaliationData;
import github.fdevos.mscreditappraiser.domain.model.UserAvaliationReturn;
import github.fdevos.mscreditappraiser.domain.model.UserSituation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity consultUserSituation(@RequestParam("cpf") String cpf) {
        UserSituation userSituation = null;
        try {
            userSituation = creditAppraiserService.findUserSituation(cpf);
            return ResponseEntity.ok(userSituation);
        } catch (UserDataNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (MicroserviceCommunicationErrorException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }

    }

    @PostMapping
    public ResponseEntity makeAvaliation(@RequestBody AvaliationData data) {
        try {
           UserAvaliationReturn avaliationReturn = creditAppraiserService.makeAvaliation(data.getCpf(), data.getIncome());
           return ResponseEntity.ok(avaliationReturn);
        } catch (UserDataNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (MicroserviceCommunicationErrorException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }
}
