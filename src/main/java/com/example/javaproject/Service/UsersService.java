package com.example.javaproject.Service;

import com.example.javaproject.DTO.UserDto;
import com.example.javaproject.DTO.UserInfoDto;
import com.example.javaproject.Repository.UsersRepository;
import com.example.javaproject.Table.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public UserInfoDto getUserInfoByUsername(String userid) {
        Optional<Users> user = usersRepository.findByUserid(userid);
        System.out.println(userid);
        if (user.isPresent()) {
            System.out.println(user.get().getUsername());
            Users foundUser = user.get();
            return new UserInfoDto(foundUser.getUserid(), foundUser.getUsername(),
                    foundUser.getScore(), foundUser.getSuccess(), foundUser.getFailed());
        } else {
            return null;
        }
    }
}
