/*
    ARQUIVO ARTISTREPOSITORY.JAVA: AQUI É ONDE IRA FAZER A PONTE DO JAVA/SPRING BOOT COM O MYSQLQ WORKBENCH
*/

package com.example.demo.repository;

import com.example.demo.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
}