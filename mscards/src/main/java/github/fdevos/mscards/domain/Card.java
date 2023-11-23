package github.fdevos.mscards.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Entity
@NoArgsConstructor
@Data
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private CardFlag holder;
    private BigDecimal income;
    private BigDecimal initialLimit;

    public Card(String name, CardFlag holder, BigDecimal income, BigDecimal initialLimit) {
        this.name = name;
        this.holder = holder;
        this.income = income;
        this.initialLimit = initialLimit;
    }


}
