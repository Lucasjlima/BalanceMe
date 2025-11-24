package br.com.fiap.balanceMe.user.controller;


import br.com.fiap.balanceMe.user.dto.request.UserUpdateRequest;
import br.com.fiap.balanceMe.user.dto.response.UserResponse;
import br.com.fiap.balanceMe.user.dto.response.UserUpdateResponse;
import br.com.fiap.balanceMe.user.entity.User;
import br.com.fiap.balanceMe.user.mapper.UserMapper;
import br.com.fiap.balanceMe.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "User", description = "Gerenciamento de usuarios")
@SecurityRequirement(name = "bearer")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService service;

    @Operation(summary = "Busca todas os usuários, apenas para administradores")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponse>> getAll() {
        return ResponseEntity.ok(service.findAll()
                .stream()
                .map(UserMapper::toUserResponse)
                .toList());
    }

    @Operation(summary = "Edita um usuário existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário editado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Meta nao encontrada")
    })
    @PatchMapping("edit/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<UserUpdateResponse> editUser(@PathVariable Long id, @RequestBody @Valid UserUpdateRequest request) {
        return service.edit(request, id)
                .map(user -> ResponseEntity.ok(UserMapper.toUserUpdateResponse(user)))
                .orElse(ResponseEntity.notFound().build());
    }
}
