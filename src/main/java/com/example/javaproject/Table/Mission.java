package com.example.javaproject.Table;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Entity
@Setter
@Getter
@ToString
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Integer userId;
    String title;
    String description;
    String deadline;
    Integer level;
    Integer maxParticipants;
    String reward;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "mission_categories",
            joinColumns = @JoinColumn(name = "mission_id")
    )
    @Column(name = "category")
    List<String> categories;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "mission_rules",
            joinColumns = @JoinColumn(name = "mission_id")
    )
    @Column(name = "rule")
    List<String> rules;
}