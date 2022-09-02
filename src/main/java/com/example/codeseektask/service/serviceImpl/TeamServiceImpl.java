package com.example.codeseektask.service.serviceImpl;

import com.example.codeseektask.dto.TeamDto;
import com.example.codeseektask.mapper.TeamMapper;
import com.example.codeseektask.model.Player;
import com.example.codeseektask.model.Team;
import com.example.codeseektask.repository.PlayerRepository;
import com.example.codeseektask.repository.TeamRepository;
import com.example.codeseektask.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

import static java.lang.String.format;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TeamServiceImpl implements TeamService {

    private final TeamMapper teamMapper;
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    @Override
    @Transactional(readOnly = true)
    public List<TeamDto> findAll() {
        return teamRepository.findAll().stream().map(teamMapper::toDto).collect(toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeamDto> findById(Integer id) {
        return teamRepository.findById(id).stream().map(teamMapper::toDto).collect(toList());
    }

    @Override
    @Transactional
    public TeamDto create(TeamDto teamDto) {
        if (nonNull(teamDto.getId())) {
            update(teamDto);
        }
        return teamMapper.toDto(teamRepository.save(teamMapper.toEntity(teamDto)));
    }

    @Override
    @Transactional
    public TeamDto update(TeamDto teamDto) {
        Team team = teamRepository.save(teamMapper.toEntity(teamDto));
        TeamDto updatedDto = teamMapper.toDto(team);
        updatedDto.setPlayerIds(team.getPlayers().stream().map(Player::getId).collect(toList()));
        return updatedDto;
    }

    @Override
    @Transactional
    public void deleteBy(Integer id) {
        teamRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public String transferPlayer(Integer sellerTeamId, Integer playerId, Integer buyerTeamId) {
        Team sellerTeam = teamRepository.findById(sellerTeamId)
                .orElseThrow(() -> new IllegalArgumentException(format("Team with id %d does not exist", sellerTeamId)));
        Team buyerTeam = teamRepository.findById(buyerTeamId)
                .orElseThrow(() -> new IllegalArgumentException(format("Team with id %d does not exist", buyerTeamId)));
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new IllegalArgumentException(format("Player with id %d does not exist", playerId)));

        Integer totalPrice = calculateTotalPrice(player, buyerTeam);

        if (buyerTeam.getWallet()-totalPrice < 0) return "Sorry, you haven't enough money";

        sellerTeam.setWallet(sellerTeam.getWallet() + totalPrice);
        buyerTeam.setWallet(buyerTeam.getWallet() - totalPrice);
        player.setTeam(buyerTeam);

        playerRepository.save(player);
        teamRepository.save(sellerTeam);
        teamRepository.save(buyerTeam);
        return "The transfer was successful!";
    }

    private Integer calculateTotalPrice(Player player, Team buyerTeam) {
        Integer priceOfPlayer = player.getExperience() * 100000 / player.getYears();
        Integer commission = priceOfPlayer * buyerTeam.getCommission() / 100;
        return priceOfPlayer + commission;
    }
}
