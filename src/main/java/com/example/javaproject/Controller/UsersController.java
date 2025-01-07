package com.example.javaproject.Controller;

import com.example.javaproject.DTO.UserDto;
import com.example.javaproject.DTO.UserInfoDto;
import com.example.javaproject.Service.UsersService;
import com.example.javaproject.Table.Users;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.Base64;

@Controller
@CrossOrigin(origins = "https://jjol.netlify.app")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;
    @Value("${jwt.secret-key}")
    private String secretKeyBase64;
    private SecretKey SECRET_KEY;

    @PostConstruct
    public void init() {
        // Base64로 인코딩된 키를 디코딩하여 SecretKey 생성
        byte[] decodedKey = Base64.getDecoder().decode(secretKeyBase64);
        SECRET_KEY = Keys.hmacShaKeyFor(decodedKey);
    }

    @PostMapping("/member")
    @ResponseBody
    public String addUser(@RequestBody UserDto userDto) {
        return usersService.addUser(userDto.getUserId(), userDto.getUsername(), userDto.getPassword());
    }

    @GetMapping("/info")
    public ResponseEntity<?> getUserInfo() {
        try {
            // 유저 정보 조회 (토큰 없이 직접적으로 처리)
            UserInfoDto userInfo = usersService.getUserInfoByUsername("defaultUsername");  // 예시로 "defaultUsername" 사용, 실제로는 로그인한 사용자나 다른 방식으로 처리해야 할 수 있음

            if (userInfo != null) {
                return ResponseEntity.ok(userInfo);
            } else {
                return ResponseEntity.status(404).body("유저 정보를 찾을 수 없습니다.");
            }

        } catch (Exception e) {
            return ResponseEntity.status(500).body("서버 오류: " + e.getMessage());
        }
    }

    private String getUsernameFromJwt(String token) throws Exception {
        try {
            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();  // JWT에서 사용자 아이디를 추출
        } catch (Exception e) {
            throw new Exception("JWT 파싱 실패: " + e.getMessage());
        }
    }
}
