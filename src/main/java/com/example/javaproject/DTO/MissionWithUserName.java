package com.example.javaproject.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MissionWithUserName {
    private Integer id;
    private Integer userId;
    private String title;
    private String description;
    private String deadline;
    private Integer level;
    private Integer maxParticipants;
    private String reward;
    private List<String> categories;
    private List<String> rules;
    private String userName;  // 추가된 사용자 이름 필드

    public MissionWithUserName(Integer id, Integer userId, String title, String description, String deadline, Integer level, Integer maxParticipants, String reward, List<String> categories, List<String> rules, String userName) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.level = level;
        this.maxParticipants = maxParticipants;
        this.reward = reward;
        this.categories = categories;
        this.rules = rules;
        this.userName = userName;
    }
}
