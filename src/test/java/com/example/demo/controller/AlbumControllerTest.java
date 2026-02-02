package com.example.demo.controller;

import com.example.demo.dto.AlbumRequestDTO;
import com.example.demo.model.Album;
import com.example.demo.service.AlbumService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AlbumController.class)
class AlbumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlbumService albumService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveCriarAlbumComSucesso() throws Exception {
        AlbumRequestDTO dto = new AlbumRequestDTO(
                "Hybrid Theory",
                LocalDate.of(2000, 10, 24),
                1L
        );

        Album album = new Album();
        album.setId(1L);
        album.setTitle("Hybrid Theory");

        when(albumService.create(dto)).thenReturn(album);

        mockMvc.perform(post("/v1/albums")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Hybrid Theory"));
    }

    @Test
    void deveListarAlbunsComPaginacao() throws Exception {
        Album album = new Album();
        album.setId(1L);
        album.setTitle("Hybrid Theory");

        Page<Album> page = new PageImpl<>(
                List.of(album),
                PageRequest.of(0, 10),
                1
        );

        when(albumService.list(null, null, PageRequest.of(0, 10)))
                .thenReturn(page);

        mockMvc.perform(get("/v1/albums")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].title")
                        .value("Hybrid Theory"));
    }
}
