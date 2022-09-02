package com.example.codeseektask.mapper;

import com.example.codeseektask.dto.TeamDto;
import com.example.codeseektask.model.Player;
import com.example.codeseektask.model.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = IGNORE,
        implementationPackage = "com.example.codeseektask.mapper.impl",
        uses = PlayerMapper.class
)
public abstract class TeamMapper {

    @Mapping(source = "team.players", target = "playerIds", qualifiedByName = "playersToIds")
    public abstract TeamDto toDto(Team team);

    @Mapping(target = "players", ignore = true)
    public abstract Team toEntity(TeamDto teamDto);

    @Named("playersToIds")
    public Set<Integer> playersToIds(Collection<Player> players) {
        return players.stream().map(Player::getId).collect(Collectors.toSet());
    }
}
