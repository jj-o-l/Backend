package com.example.javaproject.Service;

import com.example.javaproject.DTO.MissionWithUserName;
import com.example.javaproject.Repository.MissionRepository;
import com.example.javaproject.Repository.UsersRepository;
import com.example.javaproject.Table.Mission;
import com.example.javaproject.Table.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MissionService {
    private final MissionRepository missionRepository;
    private final UsersRepository usersRepository;

    public void createMission(Mission mission) {
        missionRepository.save(mission);
    }

    public List<Mission> getAllMissions() {
        return missionRepository.findAll();
    }

    public Optional<Mission> getMissionById(Integer id) {
        return missionRepository.findById(id);
    }

    public void deleteMission(Integer id) {
        missionRepository.deleteById(id);
    }
}
