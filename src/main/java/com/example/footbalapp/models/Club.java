package com.example.footbalapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Club")
@Table(name = "club")
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String division;
    private Integer foundingYear;
    private Integer numberOfPlayers;

    public Club(String name, String division, Integer foundingYear, Integer numberOfPlayers) {
        this.name = name;
        this.division = division;
        this.foundingYear = foundingYear;
        this.numberOfPlayers = numberOfPlayers;
    }
}
