package com.example.codeseektask.rest;

import com.example.codeseektask.dto.TeamDto;
import com.example.codeseektask.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TeamController {

    private final TeamService teamService;

    @GetMapping("/teams")
    public ResponseEntity<List<TeamDto>> getAll() {
        return ResponseEntity.ok(teamService.findAll());
    }

    @GetMapping("/teams/{id}")
    public ResponseEntity<List<TeamDto>> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(teamService.findById(id));
    }

    @PostMapping("/teams")
    public ResponseEntity<TeamDto> create(@RequestBody @Validated TeamDto teamDto) {
        return ResponseEntity.ok(teamService.create(teamDto));
    }

    @PutMapping("/teams/{id}")
    public ResponseEntity<TeamDto> update(@PathVariable Integer id,
                                          @RequestBody @Validated TeamDto teamDto) {
        if (!Objects.equals(id, teamDto.getId())) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(teamService.update(teamDto));
    }

    @DeleteMapping("/teams/{id}")
    public void deleteById(@PathVariable Integer id) {
        teamService.deleteBy(id);
    }

    @PutMapping("/sellerTeam/{sellerTeamId}/player/{playerId}/buyerTeam/{buyerTeamId}")
    public ResponseEntity<String> transfer(@PathVariable Integer sellerTeamId,
                                             @PathVariable Integer playerId,
                                             @PathVariable Integer buyerTeamId) {
        return ResponseEntity.ok(teamService.transferPlayer(sellerTeamId, playerId, buyerTeamId));
    }
}
