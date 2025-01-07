package com.example.javaproject.Controller;

import com.example.javaproject.DTO.LoginRequest;
import com.example.javaproject.Service.UsersService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;

    @Value("${jwt.secret-key}")
    private String secretKeyBase64;
    private SecretKey SECRET_KEY;

    @PostConstruct
    public void init() {
        // Base64로 인코딩된 키를 디코딩하여 SecretKey 생성
        byte[] decodedKey = Base64.getDecoder().decode(secretKeyBase64);
        SECRET_KEY = Keys.hmacShaKeyFor(decodedKey);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        try {
            // 사용자 인증 시도
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginRequest.getUserId(), loginRequest.getPassword());
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            System.out.println("User ID: " + loginRequest.getUserId());
            System.out.println("Password: " + loginRequest.getPassword());

            // JWT 토큰 생성
            String jwtToken = generateJwtToken(authentication);

            // 쿠키에 JWT 토큰 설정
            ResponseCookie cookie = ResponseCookie.from("auth_token", jwtToken)
                    .httpOnly(true)      // JavaScript에서 접근 불가
//                    .sameSite("None")     // 배포 환경에서는 None 사용
//                    .secure(true)         // HTTPS 환경에서는 secure=true
                    .sameSite("Lax")
                    .secure(false)
                    .path("/")           // 전체 경로에서 유효
//                    .domain("jjol.netlify.app") // 배포된 도메인
                    .maxAge(3600)        // 쿠키 만료 시간 (1시간)
                    .build();

            // 쿠키를 응답 헤더에 추가
            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, cookie.toString())  // 쿠키를 응답 헤더에 추가
                    .body("로그인 성공, 쿠키 설정 완료");

        } catch (Exception e) {
            System.out.println("Login Error: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(401).body("로그인 실패: 잘못된 아이디 또는 비밀번호입니다.");
        }
    }

    private String generateJwtToken(Authentication authentication) {
        // JWT 토큰 생성 (예시로 사용자의 아이디를 포함)
        return Jwts.builder()
                .setSubject(authentication.getName())  // 사용자 아이디 (username)
                .setIssuedAt(new Date())               // 토큰 발급 시간
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))  // 만료 시간 (1시간)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)  // 서명
                .compact();
    }
}
