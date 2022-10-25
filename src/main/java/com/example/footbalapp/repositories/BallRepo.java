package com.example.footbalapp.repositories;

import com.example.footbalapp.models.Ball;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BallRepo extends JpaRepository<Ball, Long> {
}
