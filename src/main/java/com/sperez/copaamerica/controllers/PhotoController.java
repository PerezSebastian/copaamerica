package com.sperez.copaamerica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sperez.copaamerica.entities.Photo;
import com.sperez.copaamerica.exceptions.MiException;
import com.sperez.copaamerica.services.PhotoService;

@Controller
@RequestMapping("/imagen")
public class PhotoController {
    
    @Autowired
    private PhotoService ps;

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> foto(@PathVariable String id) {
        try {
            Photo photo = ps.getOne(id);
            byte[] p = photo.getContent();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.valueOf(photo.getMime()));
            return new ResponseEntity<>(p, headers, HttpStatus.OK);
        } catch (MiException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
