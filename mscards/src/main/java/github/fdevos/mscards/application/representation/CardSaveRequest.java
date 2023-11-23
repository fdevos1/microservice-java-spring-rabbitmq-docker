package github.fdevos.mscards.application.representation;

import github.fdevos.mscards.domain.Card;
import github.fdevos.mscards.domain.CardFlag;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardSaveRequest {
private String name;
private CardFlag flag;
private BigDecimal income;
private BigDecimal limit;

public Card toModel() {
    return new Card(name, flag, income, limit);
}
}
