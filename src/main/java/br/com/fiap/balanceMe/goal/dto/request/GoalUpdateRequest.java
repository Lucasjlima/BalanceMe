package br.com.fiap.balanceMe.goal.dto.request;

import br.com.fiap.balanceMe.goal.entity.Category;
import br.com.fiap.balanceMe.goal.entity.Frequency;
import br.com.fiap.balanceMe.goal.entity.Status;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.io.Serializable;
import java.time.LocalTime;

public record GoalUpdateRequest(
        Category category,
        Frequency frequency,
        Status status,
        String unitMeasure,
        Boolean isActive,
        @PastOrPresent(message = "O horario de início não pode ser no futuro")
        LocalTime startDate,
        @FutureOrPresent(message = "A data deve ser hoje ou no futuro")
        LocalTime endDate
) implements Serializable {
}
