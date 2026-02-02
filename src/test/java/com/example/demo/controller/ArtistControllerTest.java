package com.example.demo.controller;

import com.example.demo.dto.ArtistRequestDTO;
import com.example.demo.model.Artist;
import com.example.demo.service.ArtistService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ArtistController.class)
class ArtistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArtistService artistService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveListarTodosOsArtistas() throws Exception {
        Artist artist = new Artist();
        artist.setId(1L);
        artist.setName("Linkin Park");
        artist.setType("Banda");

        when(artistService.listAll())
                .thenReturn(List.of(artist));

        mockMvc.perform(get("/v1/artists"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name")
                        .value("Linkin Park"))
                .andExpect(jsonPath("$[0].type")
                        .value("Banda"));
    }

    @Test
    void deveCriarArtistaComSucesso() throws Exception {
        ArtistRequestDTO dto = new ArtistRequestDTO(
                "Linkin Park",
                "Banda"
        );

        Artist artist = new Artist();
        artist.setId(1L);
        artist.setName("Linkin Park");
        artist.setType("Banda");

        when(artistService.create(dto))
                .thenReturn(artist);

        mockMvc.perform(post("/v1/artists")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name")
                        .value("Linkin Park"))
                .andExpect(jsonPath("$.type")
                        .value("Banda"));
    }
}
