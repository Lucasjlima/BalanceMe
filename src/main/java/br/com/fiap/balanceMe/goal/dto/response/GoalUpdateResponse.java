package br.com.fiap.balanceMe.goal.dto.response;

import br.com.fiap.balanceMe.goal.entity.Category;
import br.com.fiap.balanceMe.goal.entity.Frequency;
import br.com.fiap.balanceMe.goal.entity.Status;
import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDate;

@Builder
public record GoalUpdateResponse(
        Category category,
        Frequency frequency,
        Status status,
        String unitMeasure,
        Boolean isActive,
        LocalDate startDate,
        LocalDate endDate
) implements Serializable {
}
