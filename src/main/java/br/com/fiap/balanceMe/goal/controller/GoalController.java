package br.com.fiap.balanceMe.goal.controller;

import br.com.fiap.balanceMe.goal.dto.request.GoalRequest;
import br.com.fiap.balanceMe.goal.dto.request.GoalUpdateRequest;
import br.com.fiap.balanceMe.goal.dto.response.GoalResponse;
import br.com.fiap.balanceMe.goal.dto.response.GoalUpdateResponse;
import br.com.fiap.balanceMe.goal.entity.Goal;
import br.com.fiap.balanceMe.goal.mapper.GoalMapper;
import br.com.fiap.balanceMe.goal.service.GoalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Goal", description = "Gerenciamento de metas dos usuarios")
@SecurityRequirement(name = "bearer")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/v1/goal")
public class GoalController {
    private final GoalService service;

    @Operation(summary = "Busca todas as metas, apenas para administradores")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<GoalResponse>> findAll() {
        return ResponseEntity.ok(service.findAll()
                .stream()
                .map(GoalMapper::toGoalResponse)
                .toList());
    }

    @Operation(summary = "Lista todas as metas de um usuario especifico")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    @GetMapping("user/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<List<GoalResponse>> findByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(service.findAllGoalsByUserId(id)
                .stream()
                .map(GoalMapper::toGoalResponse)
                .toList());
    }

    @Operation(
            summary = "Busca metas paginadas, apenas para administradores"
    )
    @PageableAsQueryParam
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    @GetMapping("paged")
    @PreAuthorize("hasRole('ADMIN')")
    public Page<GoalResponse> getPaged(
            @ParameterObject @PageableDefault(sort = "id", size = 10) Pageable pageable
    ) {
        return service.pagedGoals(pageable)
                .map(GoalMapper::toGoalResponse);
    }


    @Operation(summary = "Cria uma nova meta")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Meta criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisicao invalida")
    })
    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or #request.user.id == authentication.principal.id")
    public ResponseEntity<GoalResponse> save(@RequestBody @Valid GoalRequest request) {
        Goal newGoal = GoalMapper.toGoal(request);
        Goal savedGoal = service.create(newGoal);
        return ResponseEntity.status(HttpStatus.CREATED).body(GoalMapper.toGoalResponse(savedGoal));
    }

    @Operation(summary = "Marca uma meta como completa")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Meta marcada como completa com sucesso"),
            @ApiResponse(responseCode = "403", description = "Acesso negado"),
            @ApiResponse(responseCode = "404", description = "Meta nao encontrada")
    })
    @PatchMapping("{id}/complete")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<Void> completeGoal(@PathVariable Long id) {
        service.completeGoal(id).orElseThrow();
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Edita uma meta existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Meta editada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Meta nao encontrada")
    })
    @PatchMapping("{id}/edit")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<GoalUpdateResponse> edit(@PathVariable Long id,
                                                   @RequestBody @Valid GoalUpdateRequest request) {
        return service.edit(request, id)
                .map(goal -> ResponseEntity.ok(GoalMapper.toGoalUpdateResponse(goal)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Deleta uma meta existente")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Meta deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Meta nao encontrada")
    })
    @DeleteMapping("{id}/delete")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
