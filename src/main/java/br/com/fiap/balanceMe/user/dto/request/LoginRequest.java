package br.com.fiap.balanceMe.user.dto.request;

import lombok.Builder;

@Builder
public record LoginRequest(
        String userEmail,
        String password
) {
}
