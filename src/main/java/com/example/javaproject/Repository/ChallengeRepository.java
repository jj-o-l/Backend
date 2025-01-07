package com.example.javaproject.Repository;

import com.example.javaproject.Table.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChallengeRepository extends JpaRepository<Challenge, Integer> {
    List<Challenge> findALlByMissionId(Integer missionId);
}
