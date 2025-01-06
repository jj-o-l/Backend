package com.example.javaproject.Table;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
public class Users {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(unique = true, nullable = false)
    String userid;
    @Column(nullable = false)
    String username;
    String password;
    Integer score;
    Integer success;
    Integer failed;
}
