package br.com.fiap.balanceMe.goal.dto.response;

import br.com.fiap.balanceMe.goal.entity.Category;
import br.com.fiap.balanceMe.goal.entity.Frequency;
import br.com.fiap.balanceMe.goal.entity.Status;
import lombok.Builder;

import java.io.Serializable;
import java.time.LocalTime;

@Builder
public record GoalUpdateResponse(
        Category category,
        Frequency frequency,
        Status status,
        String unitMeasure,
        Boolean isActive,
        LocalTime startDate,
        LocalTime endDate
) implements Serializable {
}
