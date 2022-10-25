package com.example.footbalapp.controllers;

import com.example.footbalapp.models.Player;
import com.example.footbalapp.repositories.PlayerRepo;
import com.example.footbalapp.services.PlayerService;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlayerController {

    private PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/get-all-players")
    public List<Player> getAllPlayers(){
        return playerService.getPlayers(); }

    @PostMapping("/add-player")
    public void addPlayer( @RequestBody Player p){
        playerService.addPlayer(p);
    }

    @GetMapping("/get-player-by-id/{id}")
    public Player getPlayerById(@PathVariable long id){
        return playerService.getPlayerById(id);
    }

    @DeleteMapping("/delete-by-name")
    public void deleteByName(@RequestParam(name = "name") String name){
        playerService.deletePlayerByName(name);
    }

    @PutMapping("/update-by-id/{id}")
    public void updateById(@PathVariable long id, @RequestBody Player p){
        playerService.updatePlayerById(id, p);
    }

    @PutMapping("/update-age-by-id/{id}")
    public void updateAgeById(@PathVariable long id, @RequestParam(value = "age") int age){
        playerService.updateAgeById(id,age);
    }
}
