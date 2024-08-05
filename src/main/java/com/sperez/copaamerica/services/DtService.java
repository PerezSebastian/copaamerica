package com.sperez.copaamerica.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sperez.copaamerica.entities.Dt;
import com.sperez.copaamerica.entities.Photo;
import com.sperez.copaamerica.entities.Team;
import com.sperez.copaamerica.exceptions.MiException;
import com.sperez.copaamerica.repositories.DtRepository;

import jakarta.transaction.Transactional;

@Service
public class DtService {

    @Autowired
    private DtRepository dtr;

    @Autowired
    private TeamService ts;

    @Autowired
    private PhotoService ps;

    @Transactional
    public void createDt(String name, String surname, Integer idTeam, MultipartFile file) throws MiException{
        validar(name, surname, idTeam);
        Team team = ts.getOneID(idTeam);
        Dt dt = new Dt();
        dt.setName(name);
        dt.setSurname(surname);
        dt.setTeam(team);
        Photo photo = ps.createPhoto(file);
        dt.setPhoto(photo);
        dtr.save(dt);
    }

    public List<Dt> listAll() {
        List<Dt> dtList = new ArrayList<>();
        dtList = dtr.findAll();
        return dtList;
    }

    private void validar(String name, String surname, Integer idTeam) throws MiException {
        if (name == null) {
            throw new MiException("el nombre no puede ser nulo");
        }
        if (name.isEmpty()) {
            throw new MiException("el nombre no puede estar vacio");
        }
        if (surname == null) {
            throw new MiException("el apellido no puede ser nulo");
        }
        if (surname.isEmpty()) {
            throw new MiException("el apellido no puede estar vacio");
        }
        if (idTeam == 0 || idTeam==null) {
            throw new MiException("Debe seleccionar un equipo");
        }
    }
}
