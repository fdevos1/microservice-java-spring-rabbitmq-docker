package github.fdevos.mscards.application.representation;

import github.fdevos.mscards.domain.ClientCard;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardsByClientResponse {
    private String name;
    private String flag;
    private BigDecimal initialLimit;

    public static CardsByClientResponse fromModel(ClientCard model) {
        return new CardsByClientResponse(
                model.getCard().getName(),
                model.getCard().getFlag().toString(),
                model.getLimit();
        )
    }

}
