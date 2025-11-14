package br.com.fiap.balanceMe.goal.dto.request;

import br.com.fiap.balanceMe.goal.entity.Category;
import br.com.fiap.balanceMe.goal.entity.Frequency;
import br.com.fiap.balanceMe.goal.entity.Goals;
import br.com.fiap.balanceMe.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO for {@link Goals}
 */
public record GoalsRequest(
        @NotNull
        User user,
        @NotNull(message = "Informe o tipo de categoria")
        Category category,
        @NotNull(message = "Informe uma frequência")
        Frequency frequency,
        @NotBlank(message = "Informe uma unidade de medida")
        String unitMeasure,
        @NotNull LocalDateTime createdAt,
        Boolean isActive,
        @NotNull
        LocalDate startDate,
        LocalDate endDate
) implements Serializable {
}