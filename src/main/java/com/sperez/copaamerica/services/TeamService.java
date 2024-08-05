package com.sperez.copaamerica.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sperez.copaamerica.entities.Team;
import com.sperez.copaamerica.exceptions.MiException;
import com.sperez.copaamerica.repositories.TeamRepository;

import jakarta.transaction.Transactional;

@Service
public class TeamService {

    @Autowired
    private TeamRepository tr;

    public List<Team> listAll(){
        List<Team> teams = new ArrayList<>();
        teams = tr.findAll();
        return teams;
    }

    @Transactional
    public void createTeam(String name, String shortname) throws MiException{
        validar(name, shortname);
        Team team = new Team();
        team.setName(name);
        team.setShortName(shortname);
        tr.save(team);
    }

    public Team getOne(String name){
        return tr.findByName(name);
    }

    public Team getOneID(Integer id) throws MiException{
        Optional<Team> resp = tr.findById(id);
        if (!resp.isPresent()) {
            throw new MiException("El equipo que intenta ingresar no existe");
        }
        return resp.get();
    }

    private void validar(String name, String shortname) throws MiException{
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
}
