package br.com.fiap.balanceMe.goal.dto.response;

import br.com.fiap.balanceMe.goal.entity.Category;
import br.com.fiap.balanceMe.goal.entity.Frequency;
import br.com.fiap.balanceMe.goal.entity.Goal;
import br.com.fiap.balanceMe.goal.entity.Status;
import br.com.fiap.balanceMe.user.entity.User;
import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * DTO for {@link Goal}
 */
@Builder
public record GoalResponse(
        Long id,
        User user,
        Category category,
        Frequency frequency,
        Status status,
        String unitMeasure,
        LocalDateTime createdAt,
        Boolean isActive,
        LocalTime startDate,
        LocalTime endDate
) implements Serializable {
}