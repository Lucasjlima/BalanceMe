package br.com.fiap.balanceMe.user.dto.response;

import br.com.fiap.balanceMe.goal.entity.Goal;
import br.com.fiap.balanceMe.user.entity.Role;
import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link br.com.fiap.balanceMe.user.entity.User}
 */
@Builder
public record UserResponse(
        Long id,
        String username,
        String userEmail,
        LocalDateTime createdAt,
        Boolean isActive,
        String timezone,
        Role role,
        List<Goal> goals
) implements Serializable {
}