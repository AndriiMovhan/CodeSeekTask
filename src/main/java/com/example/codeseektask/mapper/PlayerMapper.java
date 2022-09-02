package com.example.codeseektask.mapper;

import com.example.codeseektask.dto.PlayerDto;
import com.example.codeseektask.model.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = IGNORE,
        implementationPackage = "com.example.codeseektask.mapper.impl"
)
public abstract class PlayerMapper {

    @Mapping(source = "team.id", target = "teamId")
    public abstract PlayerDto toDto(Player player);

    @Mapping(source = "teamId", target = "team.id")
    public abstract Player toEntity(PlayerDto playerDto);
}
