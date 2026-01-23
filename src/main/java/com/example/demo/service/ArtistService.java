package com.example.demo.service;

import com.example.demo.dto.ArtistRequestDTO;
import com.example.demo.model.Artist;
import com.example.demo.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {

    @Autowired
    private ArtistRepository repository;

    public Artist create(ArtistRequestDTO dto) {
        Artist artist = new Artist();
        artist.setName(dto.name());
        artist.setType(dto.type());

        return repository.save(artist);
    }

    public List<Artist> listAll() {
        return repository.findAll();
    }
}
