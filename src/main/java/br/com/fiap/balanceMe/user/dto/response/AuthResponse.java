package br.com.fiap.balanceMe.user.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
public record AuthResponse(
        String token
) {
}
