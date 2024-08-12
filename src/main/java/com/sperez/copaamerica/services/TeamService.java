package com.sperez.copaamerica.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sperez.copaamerica.entities.Photo;
import com.sperez.copaamerica.entities.Team;
import com.sperez.copaamerica.exceptions.MiException;
import com.sperez.copaamerica.repositories.TeamRepository;

import jakarta.transaction.Transactional;

@Service
public class TeamService {

    @Autowired
    private TeamRepository tr;

    @Autowired
    private PhotoService photoService;

    public List<Team> listAll() {
        List<Team> teams = new ArrayList<>();
        teams = tr.findAll();
        return teams;
    }

    @Transactional
    public void createTeam(String name, String shortname) throws MiException {
        validar(name, shortname);
        Team team = new Team();
        team.setName(name);
        team.setShortName(shortname);
        tr.save(team);
    }

    @Transactional
    public void modifyTeam(Integer idTeam, String aka, Integer debut, Integer appearances, String history, String trophies, String maxStriker, MultipartFile flag, MultipartFile teamphoto, MultipartFile pincelflag) throws MiException{
        Team team = getOneID(idTeam);
        validar(aka, debut, appearances, history, trophies, maxStriker);
        team.setAka(aka);
        team.setDebut(debut);
        team.setAppearances(appearances);
        team.setHistory(history);
        team.setTrophies(trophies);
        team.setMaxStriker(maxStriker);
        Photo flagg = photoService.createPhoto(flag);
        team.setFlag(flagg);
        Photo teamphotoo = photoService.createPhoto(teamphoto);
        team.setTeamphoto(teamphotoo);
        Photo pincelflagg = photoService.createPhoto(pincelflag);
        team.setPincelflag(pincelflagg);
        tr.save(team);
    }

    public Team getOne(String name) throws MiException{
        Team team = tr.findByName(name);
        if (team == null) {
            throw new MiException("No existe un equipo llamado: "+name);
        }
        return team;
    }

    public Team getOneID(Integer id) throws MiException {
        Optional<Team> resp = tr.findById(id);
        if (!resp.isPresent()) {
            throw new MiException("El equipo que intenta ingresar no existe");
        }
        return resp.get();
    }

    private void validar(String name, String shortname) throws MiException {
        if (name == null) {
            throw new MiException("el nombre no puede ser nulo");
        }
        if (name.isEmpty()) {
            throw new MiException("el nombre no puede estar vacio");
        }
        if (shortname == null) {
            throw new MiException("el codigo no puede ser nulo");
        }
        if (shortname.isEmpty()) {
            throw new MiException("el codigo no puede estar vacio");
        }
    }

    private void validar(String aka, Integer debut, Integer appearances, String history, String trophies, String maxStriker) throws MiException {
        if (aka == null) {
            throw new MiException("el apodo no puede ser nulo");
        }
        if (aka.isEmpty()) {
            throw new MiException("el apodo no puede estar vacio");
        }
        if (debut == null) {
            throw new MiException("el debut no puede ser nulo");
        }
        if (appearances == null) {
            throw new MiException("Las participaciones no puede ser nulo");
        }
        if (history == null) {
            throw new MiException("el historial no puede ser nulo");
        }
        if (history.isEmpty()) {
            throw new MiException("el historial no puede estar vacio");
        }
        if (trophies == null) {
            throw new MiException("Los trofeos no pueden ser nulos");
        }
        if (trophies.isEmpty()) {
            throw new MiException("Los trofeos no pueden estar vacios");
        }
        if (maxStriker == null) {
            throw new MiException("el maximo goleador no puede ser nulo");
        }
        if (maxStriker.isEmpty()) {
            throw new MiException("el maximo goleador no puede estar vacio");
        }
    }
}
