/*
    ARQUIVO ALBUMCONTROLLER.JAVA: AQUI IMPLEMENTAMOS O METODO POST PARA A CRIAÇÃO DE NOVOS ALBUMS;
    TAMBÉM APRESENTA A LISTAGEM COMPLETA DOS ALBUMS, COM FILTROS E PAGINAÇÃO.
*/

package com.example.demo.controller;

import com.example.demo.model.Album;
import com.example.demo.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/albums")
public class AlbumController {

    @Autowired
    private AlbumRepository repository;
 
    @GetMapping
    public Page<Album> list(
            @RequestParam(required = false, defaultValue = "") String title,
            @RequestParam(required = false) String artistType,
            @PageableDefault(size = 10, sort = "title") Pageable pageable) {
        
        if (artistType != null) {
            return repository.findByTitleContainingIgnoreCaseAndArtistType(title, artistType, pageable);
        }
        return repository.findAll(pageable);
    }

    @PostMapping
    public Album create(@RequestBody Album album) {
        return repository.save(album);
    }
}