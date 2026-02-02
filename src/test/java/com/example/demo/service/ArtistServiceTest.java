package com.example.demo.service;

import com.example.demo.dto.ArtistRequestDTO;
import com.example.demo.model.Artist;
import com.example.demo.repository.ArtistRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ArtistServiceTest {

    @InjectMocks
    private ArtistService artistService;

    @Mock
    private ArtistRepository artistRepository;

    @Test
    void deveCriarArtistaComSucesso() {
        ArtistRequestDTO dto = new ArtistRequestDTO(
                "Linkin Park",
                "Banda"
        );

        when(artistRepository.save(any(Artist.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Artist artist = artistService.create(dto);

        assertNotNull(artist);
        assertEquals("Linkin Park", artist.getName());
        assertEquals("Banda", artist.getType());

        verify(artistRepository).save(any(Artist.class));
    }

    @Test
    void deveListarTodosOsArtistas() {
        Artist artist = new Artist();
        artist.setName("Linkin Park");
        artist.setType("Banda");

        when(artistRepository.findAll())
                .thenReturn(List.of(artist));

        List<Artist> artists = artistService.listAll();

        assertEquals(1, artists.size());
        assertEquals("Linkin Park", artists.get(0).getName());
        assertEquals("Banda", artists.get(0).getType());

        verify(artistRepository).findAll();
    }
}
