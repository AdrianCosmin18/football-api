package com.example.footbalapp.services;

import com.example.footbalapp.models.Player;
import com.example.footbalapp.repositories.PlayerRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    private PlayerRepo playerRepo;

    public PlayerService(PlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
    }

    public List<Player> getPlayers(){

        List<Player> players = playerRepo.findAll();
        if (players.size() == 0){
            throw new RuntimeException("ERROR: there are no players");
        }
        return players;
    }

    public void addPlayer(Player player){

        if (player.getFullName().equals("")){
            throw new RuntimeException("ERROR: player has no name");
        }
        playerRepo.save(player);
    }

    public Player getPlayerById(long id){

        Optional<Player> player = playerRepo.findById(id);
        if (player.isPresent()){
            return player.get();
        }
        throw new RuntimeException("ERROR: there is no player with this id: " + id);
    }

    public void deletePlayerByName(String name){

        Optional<Player> player = playerRepo.findPlayerByFullName(name);
        if (player.isEmpty()){
            throw new RuntimeException("ERROR: there is no player with this name: " + name);
        }
        playerRepo.deleteByFullName(name);

    }

    public void updatePlayerById(long id, Player player){

        Optional<Player> existingPlayer = playerRepo.findById(id);
        if (existingPlayer.isEmpty()){
            throw new RuntimeException("ERROR: can't update this player cause' there is no player with this id:" + id);
        }

        playerRepo.updatePlayerById(id, player.getAge(), player.getFoot(), player.getFullName(), player.getHeight(), player.getPosition());
    }

    public void updateAgeById(long id, int age){

        Optional<Player> player = playerRepo.findById(id);
        if (player.isEmpty()){
            throw new RuntimeException("ERROR: no player with this id:" + id);
        }
        playerRepo.updatePlayerAgeById(id, age);
    }
}
