package com.example.footbalapp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "Player")
@Table(name = "player")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fullName;
    private String position;
    private Integer age;
    private Double height;
    private String foot;

    public Player(String fullName, String position, Integer age, Double height, String foot) {
        this.fullName = fullName;
        this.position = position;
        this.age = age;
        this.height = height;
        this.foot = foot;
    }
}
