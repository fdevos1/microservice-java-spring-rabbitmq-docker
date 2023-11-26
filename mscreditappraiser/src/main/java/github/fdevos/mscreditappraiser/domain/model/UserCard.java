package github.fdevos.mscreditappraiser.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserCard {

    private String name;
    private String flag;
    private BigDecimal availableLimit;

}
