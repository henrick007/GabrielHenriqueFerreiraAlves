/*
    ARQUIVO AUTHCONTROLLER.JAVA: AQUI É ONDO O PROGRAMA VAI AUTENTICAR O TOKEN QUE VAIR LIBERAR O ACESSO A API
*/

package com.example.demo.controller;

import com.example.demo.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Map<String, String> data) {
        String username = data.get("username");
        String password = data.get("password");

        if ("admin".equals(username) && "admin".equals(password)) {
            var token = tokenService.generateToken(username);
            
            return ResponseEntity.ok(Map.of("token", token));
        }

        return ResponseEntity.status(403).build();
    }
}