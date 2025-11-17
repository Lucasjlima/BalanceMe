package br.com.fiap.balanceMe.user.mapper;

import br.com.fiap.balanceMe.user.dto.request.UserRequest;
import br.com.fiap.balanceMe.user.dto.response.UserResponse;
import br.com.fiap.balanceMe.user.entity.User;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class UserMapper {

    public static User toUser(UserRequest request) {
        return User
                .builder()
                .username(request.username().toLowerCase())
                .userEmail(request.userEmail())
                .createdAt(LocalDateTime.now())
                .timezone(request.timezone())
                .role(request.role())
                .build();


    }

    public static UserResponse toUserResponse(User user) {
        return UserResponse
                .builder()
                .id(user.getId())
                .username(user.getUsername().toLowerCase())
                .userEmail(user.getUserEmail())
                .createdAt(user.getCreatedAt())
                .isActive(user.getIsActive())
                .timezone(user.getTimezone())
                .role(user.getRole())
                .goals(user.getGoals())
                .build();

    }
}
