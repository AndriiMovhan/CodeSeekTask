package com.example.codeseektask.model;

import lombok.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(schema = "public", name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "\"name\"")
    private String name;

    @Column(name = "wallet")
    private Integer wallet;

    @Column(name = "commission")
    private Integer commission;

    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
    @Builder.Default
    private List<Player> players = new ArrayList<>();
}
