/*
    ARQUIVO ARTISTCONTROLLER.JAVA: AQUI VAMOS IMPLEMENTAR OS METODOS GET & POST;
    METODOS QUE VAI NOS PERMITIR LISTAR E CADASTRAR OS ARTISTAS.
 */

package com.example.demo.controller;

import com.example.demo.model.Artist;
import com.example.demo.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artists") // TODOS OS COMANDOS AQUI IRA COMEÇAR COM "localhost:8080/artists"
public class ArtistController {

    @Autowired
    private ArtistRepository repository;

    @GetMapping
    public List<Artist> listAll() {
        return repository.findAll();
    }

    @PostMapping
    public Artist create(@RequestBody Artist artist) {
        return repository.save(artist);
    }
}