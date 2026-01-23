package com.example.demo.service;

import com.example.demo.filter.SecurityFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import java.io.IOException;

@SpringBootTest // Inicia o ambiente do Spring para testar com os seus arquivos reais
@ActiveProfiles("test") // Usa o perfil de teste para não bagunçar o banco de dados real
class SecurityFilterTest {

    @Autowired
    private SecurityFilter securityFilter; // Puxa o seu "vigia" real

    @Autowired
    private TokenService tokenService; // Puxa o seu "motor de token" real

    @Test
    @DisplayName("Deveria autenticar usuário quando enviar um token válido no cabeçalho")
    void testAuthenticationWithValidToken() throws ServletException, IOException {
        
        // 1. Criamos um token legítimo usando o seu TokenService de 5 minutos
        String token = tokenService.generateToken("admin");

        // 2. Criamos "dublês" da requisição, da resposta e do fluxo da API
        // Usamos o Mockito para simular o comportamento de um navegador
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        FilterChain filterChain = Mockito.mock(FilterChain.class);

        // 3. Ensinamos ao nosso "dublê" que, quando o filtro perguntar pelo cabeçalho "Authorization",
        // ele deve responder com o nosso token no formato "Bearer [token]"
        Mockito.when(request.getHeader("Authorization")).thenReturn("Bearer " + token);

        // 4. Mandamos o seu filtro (SecurityFilter) processar essa requisição simulada
        securityFilter.doFilter(request, response, filterChain);

        // 5. Verificação Final: 
        // O Spring Security deve ter "anotado" que o usuário "admin" entrou com sucesso
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        
        Assertions.assertNotNull(authentication, "A autenticação não deveria ser nula!");
        Assertions.assertEquals("admin", authentication.getName(), "O usuário autenticado deveria ser 'admin'");

        System.out.println("SUCESSO: O filtro leu o token e liberou o acesso para o admin!");
    }
}