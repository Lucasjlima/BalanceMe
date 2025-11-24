package br.com.fiap.balanceMe.goal.dto.request;

import br.com.fiap.balanceMe.goal.entity.Category;
import br.com.fiap.balanceMe.goal.entity.Frequency;
import br.com.fiap.balanceMe.goal.entity.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


import java.io.Serializable;
import java.time.LocalTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record GoalUpdateRequest(
        Category category,
        Frequency frequency,
        Status status,
        String unitMeasure,
        Boolean isActive,
        LocalTime startDate,
        LocalTime endDate
) implements Serializable {
}
