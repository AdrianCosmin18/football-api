package com.example.footbalapp.controller;


import com.example.footbalapp.controllers.PlayerController;
import com.example.footbalapp.models.Player;
import com.example.footbalapp.repositories.PlayerRepo;
import com.example.footbalapp.services.PlayerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@ExtendWith(MockitoExtension.class)
public class PlayerControllerTest {



    @Mock
    private PlayerService playerService;



    private MockMvc mockMvc;//realizeaza post-ul

    private ObjectMapper mapper = new ObjectMapper();


    @InjectMocks
    private PlayerController playerControllerTest;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPlayers() throws Exception {

        Faker f = new Faker();
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            players.add(Player.builder().id((long) i).age(f.number().numberBetween(20, 35)).foot("right").fullName(f.name().fullName()).height((double) f.number().numberBetween((long) 1.5, (long) 2.1d)).position("mid").build());
        }
        System.out.println(players);

        doReturn(players).when(playerService).getPlayers();

        System.out.println(mockMvc);
        mockMvc.perform(MockMvcRequestBuilders.get("/get-all-players")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(players)));
    }
}
