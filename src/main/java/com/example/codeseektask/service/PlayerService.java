package com.example.codeseektask.service;

import com.example.codeseektask.dto.PlayerDto;

import java.util.List;

public interface PlayerService {
    List<PlayerDto> findAll();

    List<PlayerDto> findById(String id);

    PlayerDto create(PlayerDto playerDto);

    PlayerDto update(PlayerDto playerDto);

    void deleteBy(String id);
}
