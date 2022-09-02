package com.example.codeseektask.service.serviceImpl;

import com.example.codeseektask.dto.PlayerDto;
import com.example.codeseektask.mapper.PlayerMapper;
import com.example.codeseektask.repository.PlayerRepository;
import com.example.codeseektask.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PlayerServiceImpl implements PlayerService {

    private final PlayerMapper playerMapper;
    private final PlayerRepository playerRepository;

    @Override
    @Transactional(readOnly = true)
    public List<PlayerDto> findAll() {
        return playerRepository.findAll().stream().map(playerMapper::toDto).collect(toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlayerDto> findById(String id) {
        return playerRepository.findById(Integer.valueOf(id)).stream().map(playerMapper::toDto).collect(toList());
    }

    @Override
    @Transactional
    public PlayerDto create(PlayerDto playerDto) {
        if (nonNull(playerDto.getId())) {
            update(playerDto);
        }
        return playerMapper.toDto(playerRepository.save(playerMapper.toEntity(playerDto)));
    }

    @Override
    @Transactional
    public PlayerDto update(PlayerDto playerDto) {
        return playerMapper.toDto(playerRepository.save(playerMapper.toEntity(playerDto)));
    }

    @Override
    @Transactional
    public void deleteBy(String id) {
        playerRepository.deleteById(Integer.valueOf(id));
    }
}
