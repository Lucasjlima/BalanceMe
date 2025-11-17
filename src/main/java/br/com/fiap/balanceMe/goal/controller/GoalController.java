package br.com.fiap.balanceMe.goal.controller;

import br.com.fiap.balanceMe.goal.dto.request.GoalRequest;
import br.com.fiap.balanceMe.goal.dto.response.GoalResponse;
import br.com.fiap.balanceMe.goal.entity.Goal;
import br.com.fiap.balanceMe.goal.mapper.GoalMapper;
import br.com.fiap.balanceMe.goal.service.GoalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/v1/goal")
public class GoalController {
    private final GoalService goalService;

    @PostMapping
    public ResponseEntity<GoalResponse> save(@RequestBody @Valid GoalRequest request) {
        Goal newGoal = GoalMapper.toGoal(request);
        Goal savedGoal = goalService.create(newGoal);
        return ResponseEntity.status(HttpStatus.CREATED).body(GoalMapper.toGoalResponse(savedGoal));
    }
}
