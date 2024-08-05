package com.sperez.copaamerica.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.sperez.copaamerica.entities.Photo;
import com.sperez.copaamerica.exceptions.MiException;
import com.sperez.copaamerica.repositories.PhotoRepository;
import jakarta.transaction.Transactional;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository pr;

    @Transactional
    public Photo createPhoto(MultipartFile file) throws MiException {
        if (file == null) {
            throw new MiException("La imagen no puede ser nula");
        }
        if (file.isEmpty()) {
            throw new MiException("La imagen no puede estar vacia");
        }
        try {
            Photo photo = new Photo();
            photo.setMime(file.getContentType());
            photo.setName(file.getName());
            photo.setContent(file.getBytes());
            pr.save(photo);
            return photo;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Photo getOne(String id) throws MiException{
        Optional<Photo> resp = pr.findById(id);
        if (!resp.isPresent()) {
            throw new MiException("la foto ingresada no existe");
        }
        return resp.get();
    }

    public void update(MultipartFile file, String idPhoto) throws MiException {
        if (file == null) {
            throw new MiException("La imagen no puede ser nula");
        }
        if (idPhoto == null || idPhoto.isEmpty()) {
            throw new MiException("El id de la photo no puede estar vaci0 ni ser nulo");
        }
        try {
            Optional<Photo> resp = pr.findById(idPhoto);
            if (resp.isPresent()) {
                Photo photo = resp.get();
                photo.setMime(file.getContentType());
                photo.setName(file.getName());
                photo.setContent(file.getBytes());
                pr.save(photo);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
