package com.example.javaproject.Service;

import com.example.javaproject.DTO.MissionWithUserName;
import com.example.javaproject.Repository.MissionRepository;
import com.example.javaproject.Repository.UsersRepository;
import com.example.javaproject.Table.Mission;
import com.example.javaproject.Table.Users;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MissionService {
    private final MissionRepository missionRepository;
    private final UsersRepository usersRepository;

    public void createMission(@RequestBody Map<String, Object> requestData) {
        Mission mission = new Mission();
        mission.setUserId((Integer) requestData.get("userId"));
        mission.setTitle((String) requestData.get("title"));
        mission.setDescription((String) requestData.get("description"));
        mission.setDeadline((String) requestData.get("deadline"));
        mission.setLevel((Integer) requestData.get("level"));
        mission.setMaxParticipants((Integer) requestData.get("maxParticipants"));
        mission.setReward((String) requestData.get("reward"));

        ObjectMapper objectMapper = new ObjectMapper();
        List<String> categories = objectMapper.convertValue(requestData.get("categories"), List.class);
        mission.setCategories(categories);

        List<String> rules = objectMapper.convertValue(requestData.get("rules"), List.class);
        mission.setRules(rules);
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
