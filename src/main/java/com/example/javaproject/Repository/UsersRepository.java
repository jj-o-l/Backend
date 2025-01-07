package com.example.javaproject.Repository;

import com.example.javaproject.Table.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByUserid(String userId);
    Optional<Users> findByUsername(String username);
}
