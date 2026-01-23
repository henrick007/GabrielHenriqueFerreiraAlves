/*
    ARQUIVO ARTISTCONTROLLER.JAVA: AQUI VAMOS IMPLEMENTAR OS METODOS GET & POST;
    METODOS QUE VAI NOS PERMITIR LISTAR E CADASTRAR OS ARTISTAS.
 */

package com.example.demo.controller;

import com.example.demo.dto.ArtistRequestDTO;
import com.example.demo.model.Artist;
import com.example.demo.service.ArtistService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/artists")
public class ArtistController {

    @Autowired
    private ArtistService service;

    @GetMapping
    public List<Artist> listAll() {
        return service.listAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Artist create(@RequestBody @Valid ArtistRequestDTO dto) {
    return service.create(dto);
}

}
