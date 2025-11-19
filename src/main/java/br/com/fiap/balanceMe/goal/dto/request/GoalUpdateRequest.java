package br.com.fiap.balanceMe.goal.dto.request;

import br.com.fiap.balanceMe.goal.entity.Category;
import br.com.fiap.balanceMe.goal.entity.Frequency;

import java.io.Serializable;
import java.time.LocalDate;

public record GoalUpdateRequest(
        Category category,
        Frequency frequency,
        String unitMeasure,
        Boolean isActive,
        LocalDate startDate,
        LocalDate endDate
) implements Serializable {
}
