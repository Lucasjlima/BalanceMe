package br.com.fiap.balanceMe.goal.mapper;

import br.com.fiap.balanceMe.goal.dto.request.GoalRequest;
import br.com.fiap.balanceMe.goal.dto.response.GoalResponse;
import br.com.fiap.balanceMe.goal.dto.response.GoalUpdateResponse;
import br.com.fiap.balanceMe.goal.entity.Goal;
import br.com.fiap.balanceMe.user.entity.User;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class GoalMapper {

    public static Goal toGoal(GoalRequest request) {
        return Goal
                .builder()
                .user(request.user())
                .category(request.category())
                .frequency(request.frequency())
                .unitMeasure(request.unitMeasure())
                .createdAt(LocalDateTime.now())
                .isActive(true)
                .startDate(request.startDate())
                .endDate(request.endDate())
                .build();

    }

    public static GoalResponse toGoalResponse(Goal goal) {
        return GoalResponse
                .builder()
                .id(goal.getId())
                .user(goal.getUser())
                .category(goal.getCategory())
                .frequency(goal.getFrequency())
                .status(goal.getStatus())
                .unitMeasure(goal.getUnitMeasure())
                .createdAt(goal.getCreatedAt())
                .isActive(goal.getIsActive())
                .startDate(goal.getStartDate())
                .endDate(goal.getEndDate())
                .build();
    }

    public static GoalUpdateResponse toGoalUpdateResponse(Goal goal) {
        return GoalUpdateResponse
                .builder()
                .category(goal.getCategory())
                .frequency(goal.getFrequency())
                .unitMeasure(goal.getUnitMeasure())
                .isActive(goal.getIsActive())
                .startDate(goal.getStartDate())
                .endDate(goal.getEndDate())
                .build();
    }
}
