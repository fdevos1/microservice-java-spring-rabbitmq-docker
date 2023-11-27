package github.fdevos.mscards.application.representation;

import github.fdevos.mscards.domain.ClientCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardsByClientResponse {
    private String name;
    private String flag;
    private BigDecimal availableLimit;

    public static CardsByClientResponse fromModel(ClientCard model) {
        return new CardsByClientResponse(
                model.getCard().getName(),
                model.getCard().getHolder().toString(),
                model.getLimit()
        );
    }

}
