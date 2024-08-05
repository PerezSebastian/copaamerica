package com.sperez.copaamerica.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sperez.copaamerica.entities.Photo;
import com.sperez.copaamerica.entities.Player;
import com.sperez.copaamerica.entities.Team;
import com.sperez.copaamerica.exceptions.MiException;
import com.sperez.copaamerica.repositories.PlayerRepository;
import jakarta.transaction.Transactional;

@Service
public class PlayerService {
    
    @Autowired
    private PlayerRepository pr;

    @Autowired
    private TeamService ts;

    @Autowired
    private PhotoService ps;

    @Transactional
    public void createPlayer(String name, String surname, MultipartFile file, Integer idTeam) throws MiException{
        validar(name, surname, idTeam);
        Team team = ts.getOneID(idTeam);
        Player player = new Player();
        player.setName(name);
        player.setSurname(surname);
        player.setTeam(team);
        Photo photo = ps.createPhoto(file); 
        player.setPhoto(photo);
        pr.save(player);
    }

    public List<Player> listByTeamName(String teamName){
        List<Player> playerList = new ArrayList<>();
        playerList = pr.findByTeam(teamName);
        return playerList;
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
