package com.example.javaproject.Controller;

import com.example.javaproject.Service.MissionService;
import com.example.javaproject.Table.Mission;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MissionController {
    private final MissionService missionService;

    @PostMapping("/mission/create")
    public void createMission(@RequestBody Mission mission) {
        missionService.createMission(mission);
    }

    @GetMapping("/mission/{id}")
    public Mission getMissionById(@PathVariable Integer id) {
        return missionService.getMissionById(id)
                .orElseThrow(() -> new RuntimeException("미션이 없음 id: " + id));
    }

    @GetMapping("/missions")
    @ResponseBody
    public List<Mission> getAllMissions() {
        return missionService.getAllMissions();
    }
}
