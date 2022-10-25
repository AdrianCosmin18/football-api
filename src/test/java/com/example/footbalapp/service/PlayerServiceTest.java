package com.example.footbalapp.service;

import com.example.footbalapp.models.Player;
import com.example.footbalapp.repositories.PlayerRepo;
import com.example.footbalapp.services.PlayerService;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {

    @Mock
    private PlayerRepo playerRepo;

    @InjectMocks
    private PlayerService playerService;

    @Captor
    private ArgumentCaptor<Player> playerArgumentCaptor;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGetAllPlayers(){

        Faker f = new Faker();

        List<Player> players = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            players.add(Player.builder().age(f.number().numberBetween(20,35)).foot("right").fullName(f.name().fullName()).height((double) f.number().numberBetween((long) 1.5, (long) 2.1d)).position("mid").build());
        }

        doReturn(players).when(playerRepo).findAll();
        assertThat(playerService.getPlayers().size()).isEqualTo(players.size());
    }

    @Test
    void shouldThrowExceptionGetPlayersException(){

        List<Player> players = new ArrayList<>();
        doReturn(players).when(playerRepo).findAll();
        assertThrows(RuntimeException.class, () -> playerService.getPlayers());
    }

    @Test
    void shouldAddPlayerException(){

        Player player = Player.builder().fullName("Cosmin").age(22).build();

        doReturn(null).when(playerRepo).save(player);
        playerService.addPlayer(player);
        then(playerRepo).should().save(playerArgumentCaptor.capture());
        assertThat(playerArgumentCaptor.getValue()).isEqualTo(player);
    }

    @Test
    void shouldThrowExceptionAddPlayerException(){

        Player player = Player.builder().fullName("").age(22).build();
        assertThrows(RuntimeException.class, () -> playerService.addPlayer(player));
    }

    @Test
    void shouldGetPlayerByIdException(){
        Player player = Player.builder().id(1l).fullName("Cosmin").age(22).build();
        doReturn(Optional.of(player)).when(playerRepo).findById(player.getId());
        assertThat(playerService.getPlayerById(1l)).isEqualTo(player);
    }

    @Test
    void shouldThrowGetPlayerByIdException(){

        Player player = Player.builder().fullName("Cosmin").id(1l).age(22).build();
        doReturn(Optional.empty()).when(playerRepo).findById(player.getId());
        assertThrows(RuntimeException.class, () -> playerService.getPlayerById(1l));
    }

    @Test
    void shouldDeletePlayerByNameException(){

        Player player = Player.builder().fullName("Cosmin").id(1l).age(22).build();
        doReturn(Optional.of(player)).when(playerRepo).findPlayerByFullName(player.getFullName());
        playerService.deletePlayerByName(player.getFullName());
        then(playerRepo).should().deleteByFullName("Cosmin");
    }

    @Test
    void shouldThrowdDeletePlayerByNameException(){

        Player player = Player.builder().fullName("Cosmin").id(1l).age(22).build();
        doReturn(Optional.empty()).when(playerRepo).findPlayerByFullName(player.getFullName());
        assertThrows(RuntimeException.class, () -> playerService.deletePlayerByName("Cosmin"));
    }

    @Test
    void shouldUpdateById(){
        Player player = Player.builder().fullName("Cosmin").id(1l).age(22).build();
        Player playerUpdate = Player.builder().fullName("Adrian").foot("right").height(1.77).position("att").id(1l).age(23).build();

        doReturn(Optional.of(player)).when(playerRepo).findById(player.getId());
        playerService.updatePlayerById(1l, playerUpdate);
        then(playerRepo).should().updatePlayerById(1l, 23, "right", "Adrian", 1.77, "att");
    }

    @Test
    void shouldThrowUpdatePlayerByIdException(){

        Player playerUpdate = Player.builder().fullName("Adrian").foot("right").height(1.77).position("att").id(1l).age(23).build();
        doReturn(Optional.empty()).when(playerRepo).findById(playerUpdate.getId());
        assertThrows(RuntimeException.class, () -> playerService.updatePlayerById(1l, playerUpdate));
    }

    @Test
    void shouldUpdateAgeById(){
        Player player = Player.builder().fullName("Cosmin").id(1l).age(22).build();
        doReturn(Optional.of(player)).when(playerRepo).findById(player.getId());
        playerService.updateAgeById(player.getId(), 23);
        then(playerRepo).should().updatePlayerAgeById(1l, 23);
    }

    @Test
    void shouldThrowUpdateAgeByIdException(){
        Player player = Player.builder().fullName("Cosmin").id(1l).age(22).build();
        doReturn(Optional.empty()).when(playerRepo).findById(player.getId());
        assertThrows(RuntimeException.class, () -> playerService.updateAgeById(1l, 25));
    }

}