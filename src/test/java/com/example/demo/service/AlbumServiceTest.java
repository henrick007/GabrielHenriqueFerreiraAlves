package com.example.demo.service;

import com.example.demo.dto.AlbumRequestDTO;
import com.example.demo.model.Album;
import com.example.demo.model.Artist;
import com.example.demo.repository.AlbumRepository;
import com.example.demo.repository.ArtistRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlbumServiceTest {

    @InjectMocks
    private AlbumService albumService;

    @Mock
    private AlbumRepository albumRepository;

    @Mock
    private ArtistRepository artistRepository;

    @Mock
    private AlbumNotificationService albumNotificationService;

    @Test
    void deveCriarAlbumQuandoArtistaExistir() {
        Artist artist = new Artist();
        artist.setId(1L);

        AlbumRequestDTO dto = new AlbumRequestDTO(
                "Hybrid Theory",
                LocalDate.of(2000, 10, 24),
                1L
        );

        when(artistRepository.findById(1L))
                .thenReturn(Optional.of(artist));

        when(albumRepository.save(any(Album.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Album album = albumService.create(dto);

        assertNotNull(album);
        assertEquals("Hybrid Theory", album.getTitle());

        verify(albumRepository).save(any(Album.class));
        verify(albumNotificationService)
                .notifyNovoAlbum(any(Album.class));
    }

    @Test
    void deveLancarExcecaoQuandoArtistaNaoExistir() {
        AlbumRequestDTO dto = new AlbumRequestDTO(
                "Hybrid Theory",
                LocalDate.now(),
                99L
        );

        when(artistRepository.findById(99L))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> albumService.create(dto)
        );

        assertTrue(exception.getMessage()
                .contains("Artista não encontrado"));
    }
}
