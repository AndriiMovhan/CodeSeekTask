package com.example.codeseektask.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDto {

    private Integer id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotNull
    private Integer years;
    @NotNull
    private Integer experience;
    @NotNull
    private Integer teamId;
}
