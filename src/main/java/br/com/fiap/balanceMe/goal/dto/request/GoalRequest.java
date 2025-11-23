package br.com.fiap.balanceMe.goal.dto.request;

import br.com.fiap.balanceMe.goal.entity.Category;
import br.com.fiap.balanceMe.goal.entity.Frequency;
import br.com.fiap.balanceMe.goal.entity.Goal;
import br.com.fiap.balanceMe.user.entity.User;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.io.Serializable;
import java.time.LocalTime;

/**
 * DTO for {@link Goal}
 */
public record GoalRequest(
        User user,
        @NotNull(message = "Informe o tipo de categoria")
        Category category,
        @NotNull(message = "Informe uma frequência")
        Frequency frequency,
        @NotBlank(message = "Informe uma unidade de medida")
        String unitMeasure,
        @NotNull
        LocalTime startDate,
        @FutureOrPresent(message = "A data deve ser hoje ou no futuro")
        LocalTime endDate
) implements Serializable {
}