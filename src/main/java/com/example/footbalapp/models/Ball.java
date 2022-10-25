package com.example.footbalapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Ball")
@Table(name = "ball")
public class Ball {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String brand;
    private String color;
    private Integer size;

    public Ball(String brand, String color, Integer size) {
        this.brand = brand;
        this.color = color;
        this.size = size;
    }
}
