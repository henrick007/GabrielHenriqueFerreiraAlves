/*
    CRIANDO A TABELA ALBUMS NO BANCO DE DADOS MYSQL WORKBENCH "localhost"
*/

CREATE TABLE albums (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    release_date DATE,
    artist_id BIGINT NOT NULL,
    CONSTRAINT fk_album_artist FOREIGN KEY (artist_id) REFERENCES artists(id)
);