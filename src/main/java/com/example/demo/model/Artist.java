/*
    ARQUIVO ARTIST.JAVA: AQUI ESTA A LOGICA PARA QUE O PROGRAMA CONSIGA SALVAR UM ARTISTA/ BANDA NO BANCO DE DADOS
*/

package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "artists")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type;
}
