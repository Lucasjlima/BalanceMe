package br.com.fiap.balanceMe.user.dto.request;

import br.com.fiap.balanceMe.user.entity.Role;

import java.io.Serializable;

public record UserUpdateRequest(
        String username,
        String userEmail,
        String timezone,
        Role role
) implements Serializable {
}
