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

    public List<MissionWithUserName> getAllMissions() {
        List<Mission> missions = missionRepository.findAll();
        List<MissionWithUserName> missionsWithUserName = new ArrayList<>();

        for (Mission mission : missions) {
            Users user = usersRepository.findById(mission.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            String userName = user.getUsername();

            MissionWithUserName missionWithUserName = new MissionWithUserName(
                    mission.getId(),
                    mission.getUserId(),
                    mission.getTitle(),
                    mission.getDescription(),
                    mission.getDeadline(),
                    mission.getLevel(),
                    mission.getMaxParticipants(),
                    mission.getReward(),
                    mission.getCategories(),
                    mission.getRules(),
                    userName
            );

            missionsWithUserName.add(missionWithUserName);
        }

        return missionsWithUserName;
    }

    public Optional<Mission> getMissionById(Integer id) {
        return missionRepository.findById(id);
    }

    public void deleteMission(Integer id) {
        missionRepository.deleteById(id);
    }
}
