package br.com.fiap.balanceMe.goal.controller;

import br.com.fiap.balanceMe.goal.dto.request.GoalRequest;
import br.com.fiap.balanceMe.goal.dto.request.GoalUpdateRequest;
import br.com.fiap.balanceMe.goal.dto.response.GoalResponse;
import br.com.fiap.balanceMe.goal.dto.response.GoalUpdateResponse;
import br.com.fiap.balanceMe.goal.entity.Goal;
import br.com.fiap.balanceMe.goal.mapper.GoalMapper;
import br.com.fiap.balanceMe.goal.service.GoalService;
import br.com.fiap.balanceMe.user.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/v1/goal")
public class GoalController {
    private final GoalService service;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<GoalResponse>> findAll() {
        return ResponseEntity.ok(service.findAll()
                .stream()
                .map(GoalMapper::toGoalResponse)
                .toList());
    }

    @GetMapping("user/{id}")
    public ResponseEntity<List<GoalResponse>> findByUserId(@PathVariable Long id, @AuthenticationPrincipal User currentUser) throws Exception {
        if(!currentUser.getId().equals(id)) {
            throw new Exception("Acesso negado: Voce nao pode acessar as metas de outro usuario.");
        }
        return ResponseEntity.ok(service.findAllGoalsByUserId(id)
                .stream()
                .map(GoalMapper::toGoalResponse)
                .toList());
    }

    @GetMapping("paged")
    @PreAuthorize("hasRole('ADMIN')")
    public Page<GoalResponse> getPaged(Pageable pageable) {
        return service.pagedGoals(pageable)
                .map(GoalMapper::toGoalResponse);
    }

    @PostMapping
    public ResponseEntity<GoalResponse> save(@RequestBody @Valid GoalRequest request) {
        Goal newGoal = GoalMapper.toGoal(request);
        Goal savedGoal = service.create(newGoal);
        return ResponseEntity.status(HttpStatus.CREATED).body(GoalMapper.toGoalResponse(savedGoal));
    }

    @PatchMapping("{id}/complete")
    @PreAuthorize("hasAnyRole('ADMIN', 'DEFAULT_USER') or #id == authentication.principal.id")
    public ResponseEntity<Void> completeGoal(@PathVariable Long id) {
        service.completeGoal(id).orElseThrow();
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("{id}/edit")
    public ResponseEntity<GoalUpdateResponse> edit(@PathVariable Long id, @RequestBody @Valid GoalUpdateRequest request) {
        return service.edit(request, id)
                .map(goal -> ResponseEntity.ok(GoalMapper.toGoalUpdateResponse(goal)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
