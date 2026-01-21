/*
    ARQUIVO ALBUM.JAVA: AQUI ESTA A LOGICA PARA QUE O PROGRAMA CONSIGA SALVAR UM ALBUM NO BANCO DE DADOS, VINCULANDO AO SEU ARTISTA ESPECIFICO.
*/

package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "albums")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private java.time.LocalDate releaseDate;

    @ManyToOne // Requisito de relacionamento do edital
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist;
}