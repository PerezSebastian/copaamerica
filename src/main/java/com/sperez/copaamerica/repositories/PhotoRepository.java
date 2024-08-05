package com.sperez.copaamerica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sperez.copaamerica.entities.Photo;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, String> {
    
}
