/*
    ARQUIVO ARTISTREQUESTDTO.JAVA: CÓDIGO PARA GARANTIR QUE A API/ BANCO DE DADOS RECEBE SÓ O NECESSARIO REFERENTE AOS ARTISTAS
*/

package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;

public record ArtistRequestDTO(
    @NotBlank(message = "O nome do artista é obrigatório")
    String name,
    
    @NotBlank(message = "O tipo (carreira solo/banda) é obrigatório")
    String type
) {
}