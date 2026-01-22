package com.example.demo.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc // Ferramenta para simular chamadas HTTP na sua API
@ActiveProfiles("test")
class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Deveria permitir acesso à rota de login sem token")
    void testAccessLoginPermitted() throws Exception {
        // Tentamos um POST no login. 
        // Como o Controller não existe, o erro esperado é 404 (Não encontrado), 
        // mas NÃO pode ser 403 (Proibido) ou 401 (Não autorizado).
        mockMvc.perform(post("/v1/auth/login"))
               .andExpect(status().isNotFound()); // Prova que a segurança deixou passar e chegou na porta do controller
    }

    @Test
    @DisplayName("Deveria negar acesso a rotas protegidas sem token")
    void testAccessArtistsBlocked() throws Exception {
        // Tentamos um GET nos artistas sem enviar o Token.
        // O esperado é 403 Forbidden, provando que a tranca está funcionando.
        mockMvc.perform(get("/v1/artists"))
               .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Deveria permitir acesso ao Swagger sem token")
    void testAccessSwaggerPermitted() throws Exception {
        // Atende ao item (l) do edital
        mockMvc.perform(get("/swagger-ui/index.html"))
               .andExpect(status().isOk());
    }
}