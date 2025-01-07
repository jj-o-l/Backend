package com.example.javaproject.Controller;

import com.example.javaproject.DTO.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        try {
            // 사용자 인증 시도
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginRequest.getUserId(), loginRequest.getPassword());
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            ResponseCookie cookie = ResponseCookie.from("auth_token", "someAuthTokenHere")
                    .httpOnly(true)      // JavaScript에서 접근 불가
                    .sameSite("Lax")     // 로컬 환경에서는 Lax 사용
                    .secure(false)       // HTTP 환경이므로 secure=false
                    .path("/")           // 전체 경로에서 유효
                    .maxAge(3600)        // 쿠키 만료 시간 (1시간)
                    .build();



            // 쿠키를 응답 헤더에 추가
            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, cookie.toString())  // 쿠키를 응답 헤더에 추가
                    .body("로그인 성공, 쿠키 설정 완료");
        } catch (BadCredentialsException e) {
            // 잘못된 자격 증명 처리
            return ResponseEntity.status(401).body("로그인 실패: 잘못된 아이디 또는 비밀번호입니다.");
        } catch (Exception e) {
            // 서버 에러 처리
            return ResponseEntity.status(500).body("로그인 실패: 서버에 문제가 발생했습니다.");
        }
    }
}
