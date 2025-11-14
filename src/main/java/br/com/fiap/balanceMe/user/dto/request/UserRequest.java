package br.com.fiap.balanceMe.user.dto.request;

import br.com.fiap.balanceMe.goal.entity.Goals;
import br.com.fiap.balanceMe.user.entity.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link br.com.fiap.balanceMe.user.entity.User}
 */
public record UserRequest(
        @NotBlank(message = "Informe o nome do usuário") @Length(max = 50)
        String username,
        @NotBlank(message = "Informe o email do usuário") @Length(max = 150)
        String userEmail,
        @NotBlank
        String timezone,
        @NotNull
        Role role,
        List<Goals> goals
) implements Serializable {
}