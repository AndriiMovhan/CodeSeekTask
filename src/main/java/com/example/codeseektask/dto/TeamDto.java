package com.example.codeseektask.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamDto {

    private Integer id;
    @NotBlank
    private String name;
    @NotNull
    private Integer wallet;
    @NotNull
    private Integer commission;
    @Builder.Default
    private Collection<Integer> playerIds = new ArrayList<>();
}
