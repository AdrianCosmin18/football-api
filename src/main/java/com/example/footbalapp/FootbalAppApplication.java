package com.example.footbalapp;

import com.example.footbalapp.models.Player;
import com.example.footbalapp.repositories.PlayerRepo;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
public class FootbalAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(FootbalAppApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(PlayerRepo playerRepo){
        return args -> {
            Faker f = new Faker();

            String[]foot={"left","right"};
            for(int i=0;i<5;i++){

                Player p = new Player(f.name().fullName(), f.job().position(), f.number().numberBetween(17, 38), (double) f.number().numberBetween(160,200), f.artist().name());
                playerRepo.save(p);
            }

        };
    }

}
