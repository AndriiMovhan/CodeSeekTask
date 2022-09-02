package com.example.codeseektask.rest;

import com.example.codeseektask.dto.PlayerDto;
import com.example.codeseektask.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping("/players")
    public ResponseEntity<List<PlayerDto>> getAll() {
        return ResponseEntity.ok(playerService.findAll());
    }

    @GetMapping("/players/{id}")
    public ResponseEntity<List<PlayerDto>> getById(@PathVariable String id) {
        return ResponseEntity.ok(playerService.findById(id));
    }

    @PostMapping("/players")
    public ResponseEntity<PlayerDto> create(@RequestBody @Validated PlayerDto playerDto) {
        return ResponseEntity.ok(playerService.create(playerDto));
    }

    @PutMapping("/players/{id}")
    public ResponseEntity<PlayerDto> update(@PathVariable Integer id,
                                            @RequestBody @Validated PlayerDto playerDto) {

        if (!Objects.equals(id, playerDto.getId())) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(playerService.update(playerDto));
    }

    @DeleteMapping("/players/{id}")
    public void deleteById(@PathVariable String id) {
        playerService.deleteBy(id);
    }
}
