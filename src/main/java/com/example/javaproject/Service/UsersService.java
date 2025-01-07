package com.example.javaproject.Service;

import com.example.javaproject.Repository.UsersRepository;
import com.example.javaproject.Table.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;

    public String addUser(String userId, String username, String password) {
        Users user = new Users();
        user.setUserid(userId);
        user.setUsername(username);
        var hash = new BCryptPasswordEncoder().encode(password);
        user.setPassword(hash);
        user.setScore(0);
        user.setSuccess(0);
        user.setFailed(0);
        usersRepository.save(user);
        return "회원가입 성공";
    }
}
