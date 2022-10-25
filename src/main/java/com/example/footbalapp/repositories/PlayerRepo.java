package com.example.footbalapp.repositories;

import com.example.footbalapp.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface PlayerRepo extends JpaRepository<Player, Long> {

    Player getPlayerById(long id);

    @Transactional
    void deleteByFullName(String name);
    Optional<Player> findPlayerByFullName(String name);

    @Transactional(readOnly = false)
    @Modifying
    @Query("update Player p set p.age = :age, p.foot = :foot, p.fullName = :name, p.height = :height, p.position = :pos where p.id = :id")
    void updatePlayerById(long id, int age, String foot, String name, double height, String pos);

    @Transactional
    @Modifying
    @Query("update Player p set p.age = :age where p.id = :id")
    void updatePlayerAgeById(long id, int age);

    @Query("select count(p) from Player p where p.height >= :height")
    int countAllByHeightBiggerThan(double height);
}
