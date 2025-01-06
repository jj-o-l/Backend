package com.example.javaproject.Repository;

import com.example.javaproject.Table.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {
}
