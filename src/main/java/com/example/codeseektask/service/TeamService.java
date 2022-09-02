package com.example.codeseektask.service;

import com.example.codeseektask.dto.TeamDto;

import java.util.List;

public interface TeamService {
    List<TeamDto> findAll();

    List<TeamDto> findById(Integer id);

    TeamDto create(TeamDto teamDto);

    TeamDto update(TeamDto teamDto);

    void deleteBy(Integer id);

    /**
     *This method implements the sale of the player
     *
     * @param sellerTeamId team who sell player
     * @param playerId player which go to another team
     * @param buyerTeamId team who buy player
     * @return comment
     */
    String transferPlayer(Integer sellerTeamId, Integer playerId, Integer buyerTeamId);
}
