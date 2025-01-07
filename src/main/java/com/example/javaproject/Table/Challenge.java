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
public class Challenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Integer userId;
    Integer missionId;
    String title;
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "challenge_checkbox",
            joinColumns = @JoinColumn(name = "challenge_id")
    )
    @Column(name = "checkbox")
    List<String> checkboxes;
    Integer success;
    Integer failed;
}
