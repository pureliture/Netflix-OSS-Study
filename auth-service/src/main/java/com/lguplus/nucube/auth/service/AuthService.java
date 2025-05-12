package com.lguplus.nucube.auth.service;

import com.lguplus.nucube.auth.security.JwtTokenProvider;
import com.lguplus.nucube.auth.dto.LoginRequest;
import com.lguplus.nucube.auth.dto.LoginResponse;
import com.lguplus.nucube.auth.entity.User;
import com.lguplus.nucube.auth.repository.UserRepository;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository repo;
    private final JwtTokenProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository repo, JwtTokenProvider jwtProvider, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponse login(LoginRequest req) throws Exception {
        System.out.println("로그인 시도: " + req.getUsername()); // 디버깅 로그

        User user = repo.findByUsername(req.getUsername())
                .orElseThrow(() -> {
                    System.out.println("사용자를 찾을 수 없음: " + req.getUsername());
                    return new RuntimeException("Invalid credentials");
                });

        // 비밀번호 검증 활성화
//        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
//            System.out.println("비밀번호 불일치: " + req.getUsername());
//            throw new RuntimeException("Invalid credentials");
//        }
        if (!StringUtils.equals(req.getPassword(), user.getPassword())) {
            System.out.println("비밀번호 불일치: " + req.getUsername());
            throw new RuntimeException("Invalid credentials");
        }

        System.out.println("로그인 성공: " + req.getUsername());
        String token = jwtProvider.generateToken(user.getUsername(), user.getRoles());
        return new LoginResponse(token);
    }
}