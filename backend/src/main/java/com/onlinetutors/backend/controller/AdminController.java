 package com.onlinetutors.backend.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    // Hardcoded credentials (for now)
    private final String ADMIN_USERNAME = "Keshav@1403";
    private final String ADMIN_PASSWORD = "Keshav@1403";

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> creds,
                                   HttpSession session) {

        String username = creds.get("username");
        String password = creds.get("password");

        if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) {
            session.setAttribute("ADMIN", true);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/check")
    public boolean checkLogin(HttpSession session) {
        return session.getAttribute("ADMIN") != null;
    }

    @PostMapping("/logout")
    public void logout(HttpSession session) {
        session.invalidate();
    }
}
