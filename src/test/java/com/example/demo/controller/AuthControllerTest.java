package com.example.demo.controller;

import com.example.demo.service.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TokenService tokenService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveRetornarTokenQuandoCredenciaisForemValidas() throws Exception {
        // given
        when(tokenService.generateToken("admin"))
                .thenReturn("token-fake");

        var body = objectMapper.writeValueAsString(
                java.util.Map.of(
                        "username", "admin",
                        "password", "admin"
                )
        );

        // when / then
        mockMvc.perform(post("/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("token-fake"));
    }

    @Test
    void deveRetornar403QuandoCredenciaisForemInvalidas() throws Exception {
        var body = objectMapper.writeValueAsString(
                java.util.Map.of(
                        "username", "admin",
                        "password", "errada"
                )
        );

        mockMvc.perform(post("/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isForbidden());
    }
}
