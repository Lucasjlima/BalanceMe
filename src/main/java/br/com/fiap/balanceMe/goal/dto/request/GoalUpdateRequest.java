package br.com.fiap.balanceMe.goal.dto.request;

import br.com.fiap.balanceMe.goal.entity.Category;
import br.com.fiap.balanceMe.goal.entity.Frequency;
import br.com.fiap.balanceMe.goal.entity.Status;
import jakarta.validation.constraints.FutureOrPresent;

import java.io.Serializable;
import java.time.LocalTime;

public record GoalUpdateRequest(
        Category category,
        Frequency frequency,
        Status status,
        String unitMeasure,
        Boolean isActive,
        LocalTime startDate,
        @FutureOrPresent(message = "A data deve ser hoje ou no futuro")
        LocalTime endDate
) implements Serializable {
}
