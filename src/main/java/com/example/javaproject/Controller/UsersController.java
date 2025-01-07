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
    public ResponseEntity<?> getUserInfo(@CookieValue(value = "auth_token", defaultValue = "") String authToken) {
        if (authToken.isEmpty()) {
            return ResponseEntity.status(401).body("로그인 정보가 없습니다.");
        }

        try {
            // JWT 토큰을 사용해 유저 정보 조회
            String username = getUsernameFromJwt(authToken);
            // 서비스에서 유저 정보를 조회
            UserInfoDto userInfo = usersService.getUserInfoByUsername(username);

            if (userInfo != null) {
                return ResponseEntity.ok(userInfo);
            } else {
                return ResponseEntity.status(401).body("유효하지 않은 인증 토큰입니다.");
            }

        } catch (Exception e) {
            return ResponseEntity.status(401).body("토큰 검증 실패: " + e.getMessage());
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
