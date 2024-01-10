package github.fdevos.mscreditappraiser.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserAvaliationReturn {
    private List<ApprovedCard> cards;
}
