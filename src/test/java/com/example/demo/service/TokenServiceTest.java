package com.example.demo.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest // Carrega o contexto do Spring para ler o application.properties
@ActiveProfiles("test")
class TokenServiceTest {

    @Autowired
    private TokenService tokenService;

    @Test
    @DisplayName("Deveria gerar um token e validar o usuário corretamente")
    void testTokenGenerationAndValidation() {
        // 1. Definimos um nome de usuário para o teste
        String user = "admin";

        // 2. Mandamos o serviço gerar o token
        String token = tokenService.generateToken(user);

        // 3. Verificamos se o token não veio vazio
        Assertions.assertNotNull(token);
        Assertions.assertFalse(token.isEmpty());

        // 4. Agora mandamos o serviço validar esse mesmo token
        String validatedUser = tokenService.validateToken(token);

        // 5. O nome que o serviço extraiu do token deve ser igual ao que enviamos ("admin")
        Assertions.assertEquals(user, validatedUser);
        
        System.out.println("TESTE BEM SUCEDIDO: Token gerado e validado!");
        System.out.println("Token gerado: " + token);
    }
}