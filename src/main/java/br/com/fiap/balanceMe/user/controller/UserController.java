package br.com.fiap.balanceMe.user.controller;

import br.com.fiap.balanceMe.user.dto.request.UserRequest;
import br.com.fiap.balanceMe.user.dto.response.UserResponse;
import br.com.fiap.balanceMe.user.entity.User;
import br.com.fiap.balanceMe.user.mapper.UserMapper;
import br.com.fiap.balanceMe.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService service;


    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllv2() {
        return ResponseEntity.ok(service.findAll()
                .stream()
                .map(UserMapper::toUserResponse)
                .toList());
    }


    @PostMapping
    public ResponseEntity<UserResponse> save(@RequestBody @Valid UserRequest request) {
        User newUser = UserMapper.toUser(request);
        User savedUser = service.create(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toUserResponse(savedUser));
    }
}
