package com.example.javaproject.Repository;

import com.example.javaproject.Table.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Integer> {
}
