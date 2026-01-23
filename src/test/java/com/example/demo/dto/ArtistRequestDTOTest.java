package com.example.demo.dto;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ArtistRequestDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("Deveria invalidar DTO com campos vazios")
    void testInvalidDTO() {
        // Criamos um DTO com strings vazias (que o @NotBlank deve barrar)
        var dto = new ArtistRequestDTO("", "");
        var violations = validator.validate(dto);

        // Esperamos que existam 2 violações (erro no nome e erro no tipo)
        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(2, violations.size());
    }
}