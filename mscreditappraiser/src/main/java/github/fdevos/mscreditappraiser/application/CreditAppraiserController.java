package github.fdevos.mscreditappraiser.application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("avaliacoes-credito")
public class CreditAppraiserController {

    @GetMapping
    public String status() {
        return "OK!";
    }
}
