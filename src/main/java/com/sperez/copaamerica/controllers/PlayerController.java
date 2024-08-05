package com.sperez.copaamerica.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sperez.copaamerica.entities.Team;
import com.sperez.copaamerica.exceptions.MiException;
import com.sperez.copaamerica.services.PlayerService;
import com.sperez.copaamerica.services.TeamService;

@Controller
@RequestMapping("/jugadores")
public class PlayerController {

    @Autowired
    private TeamService ts;

    @Autowired
    private PlayerService pls;

    @GetMapping("/registrar")
    public String registrar(ModelMap model){
        List<Team> teamList = new ArrayList<>();
        teamList = ts.listAll();
        model.put("teamList",teamList);
        return "players/createPlayer";
    }

    @PostMapping("/guardar")
    public String guardar(@RequestParam String name, @RequestParam String surname, @RequestParam MultipartFile file, @RequestParam(required = false) Integer idTeam, ModelMap model){
        try {
            pls.createPlayer(name, surname, file, idTeam);;
            model.put("exito", "Jugador cargado exitosamente!");
        } catch (MiException ex) {
            System.out.println(ex.getMessage());
            model.put("error", ex.getMessage());
        }
        List<Team> teamList = new ArrayList<>();
        teamList = ts.listAll();
        model.put("teamList",teamList);
        return "players/createPlayer";
    }
}
