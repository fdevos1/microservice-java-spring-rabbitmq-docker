package github.fdevos.mscreditappraiser.infra.client;

import github.fdevos.mscreditappraiser.domain.model.UserData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "msclients", path = "/clientes")
public interface UserResourceClient {
    @GetMapping(params = "cpf")
    ResponseEntity<UserData> userData(@RequestParam("cpf") String cpf);
}
