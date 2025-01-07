package com.example.javaproject.Controller;

import com.example.javaproject.DTO.UserDto;
import com.example.javaproject.Service.UsersService;
import com.example.javaproject.Table.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class UsersController {
    private final UsersService usersService;

    @PostMapping("/member")
    @ResponseBody
    public String addUser(@RequestBody UserDto userDto) {
        return usersService.addUser(userDto.getUserId(), userDto.getUsername(), userDto.getPassword());
    }
}
