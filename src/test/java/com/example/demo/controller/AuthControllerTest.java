package com.example.demo.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc; // O simulador de chamadas HTTP

    @Test
    @DisplayName("Deveria retornar 200 e um token ao logar com credenciais validas")
    void testLoginSuccess() throws Exception {
        // Simulamos o JSON que o usuário envia no corpo da requisição
        String json = "{\"username\": \"admin\", \"password\": \"admin\"}";

        mockMvc.perform(post("/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk()) // Esperamos HTTP 200
                .andExpect(jsonPath("$.token").exists()); // Verificamos se o campo "token" veio no JSON
        
        System.out.println("SUCESSO: Login admin/admin gerou token corretamente!");
    }

    @Test
    @DisplayName("Deveria retornar 403 ao logar com credenciais invalidas")
    void testLoginFailure() throws Exception {
        // Tentativa de invasão com senha errada
        String json = "{\"username\": \"admin\", \"password\": \"senha_errada\"}";

        mockMvc.perform(post("/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isForbidden()); // Esperamos HTTP 403
        
        System.out.println("SUCESSO: Acesso negado para credenciais inválidas!");
    }
}