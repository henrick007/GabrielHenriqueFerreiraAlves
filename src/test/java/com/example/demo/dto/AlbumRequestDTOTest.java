package com.example.demo.dto;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AlbumRequestDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testInvalidAlbumDTO() {
        // Enviamos tudo nulo ou vazio
        var dto = new AlbumRequestDTO("", null, null);
        var violations = validator.validate(dto);

        // Deve encontrar 3 erros (titulo, data e artistId)
        Assertions.assertEquals(3, violations.size());
    }
}