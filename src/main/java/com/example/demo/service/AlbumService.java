package com.example.demo.service;

import com.example.demo.dto.AlbumRequestDTO;
import com.example.demo.model.Album;
import com.example.demo.model.Artist;
import com.example.demo.repository.AlbumRepository;
import com.example.demo.repository.ArtistRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Transactional
    public Album create(AlbumRequestDTO dto) {

        Artist artist = artistRepository.findById(dto.artistId())
                .orElseThrow(() -> new RuntimeException(
                        "Artista não encontrado com ID: " + dto.artistId()
                ));

        Album album = new Album();
        album.setTitle(dto.title());
        album.setReleaseDate(dto.releaseDate());
        album.setArtist(artist);

        return albumRepository.save(album);
    }

    public Page<Album> list(String title, String artistType, Pageable pageable) {

    if (artistType != null && !artistType.isBlank()) {
        return albumRepository
                .findByTitleContainingIgnoreCaseAndArtist_Type(
                        title,
                        artistType,
                        pageable
                );
    }

    return albumRepository.findAll(pageable);
}

}
