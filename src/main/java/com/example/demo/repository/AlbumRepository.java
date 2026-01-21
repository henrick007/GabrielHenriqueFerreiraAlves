/*
    ARQUIVO ALBUMREPOSITORY.JAVA: AQUI É ONDE IRA FAZER A PONTE DO JAVA/SPRING BOOT COM O MYSQLQ WORKBENCH. 
*/

package com.example.demo.repository;

import com.example.demo.model.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    Page<Album> findByTitleContainingIgnoreCaseAndArtistType(String title, String Type, Pageable pageable);
}