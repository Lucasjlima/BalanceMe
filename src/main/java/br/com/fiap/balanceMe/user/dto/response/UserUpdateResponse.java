package br.com.fiap.balanceMe.user.dto.response;

import br.com.fiap.balanceMe.user.entity.Role;
import lombok.Builder;

import java.io.Serializable;
@Builder
public record UserUpdateResponse(
        String username,
        String userEmail,
        String timezone,
        Role role
) implements Serializable {
}
