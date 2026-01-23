/*
    ARQUIVO ALBUMREQUESTDTO.JAVA: CÓDIGO PARA GARANTIR QUE A API/ BANCO DE DADOS RECEBE SÓ O NECESSARIO REFERENTE AOS ALBUMS
*/

package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record AlbumRequestDTO(
    @NotBlank(message = "O título do álbum é obrigatório")
    String title,
    
    @NotNull(message = "A data de lançamento é obrigatória")
    LocalDate releaseDate,
    
    @NotNull(message = "O ID do artista é obrigatório")
    Long artistId
) {
}