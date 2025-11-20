package br.com.fiap.balanceMe.user.dto.request;

import lombok.Builder;

@Builder
public record RegisterRequest(
        String username,
        String userEmail,
        String password,
        String timezone
) {
}
