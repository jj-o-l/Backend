package com.example.javaproject.Repository;

import com.example.javaproject.Table.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
