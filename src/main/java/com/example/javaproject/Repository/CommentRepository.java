package com.example.javaproject.Repository;

import com.example.javaproject.Table.Challenge;
import com.example.javaproject.Table.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
