package br.com.fiap.balanceMe.goal.dto.response;

import br.com.fiap.balanceMe.goal.entity.Category;
import br.com.fiap.balanceMe.goal.entity.Frequency;
import br.com.fiap.balanceMe.user.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO for {@link br.com.fiap.balanceMe.goal.entity.Goals}
 */
@Builder
public record GoalsResponse(
        Long id,
        User user,
        Category category,
        Frequency frequency,
        String unitMeasure,
        Double currentValue,
        LocalDateTime createdAt,
        Boolean isActive,
        LocalDate startDate,
        LocalDate endDate
) implements Serializable {
}