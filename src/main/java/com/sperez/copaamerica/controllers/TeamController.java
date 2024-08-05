package com.sperez.copaamerica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sperez.copaamerica.entities.Dt;
import com.sperez.copaamerica.entities.Team;
import com.sperez.copaamerica.exceptions.MiException;
import com.sperez.copaamerica.repositories.DtRepository;
import com.sperez.copaamerica.services.PlayerService;
import com.sperez.copaamerica.services.TeamService;

@Controller
@RequestMapping("/equipos")
public class TeamController {

    @Autowired
    private DtRepository dtr;

    @Autowired
    private TeamService ts;

    @Autowired
    private PlayerService pls;
    
    @GetMapping("/registrar")
    public String registrar() {
        return "teams/createTeam";
    }

    @PostMapping("/guardar")
    public String guardar(@RequestParam String name, @RequestParam String shortName, ModelMap model) {
        try {
            ts.createTeam(name, shortName.toUpperCase());
            model.put("exito", "El equipo fue cargado exitosamente");
        } catch (MiException ex) {
            model.put("error", ex.getMessage());
        }
        return "teams/createTeam";
    }

    @GetMapping("")
    public String teams(ModelMap model){
        model.put("teamList", ts.listAll());
        return "teams/teams";
    }

    @GetMapping("/{teamName}")
    public String team(@PathVariable String teamName, ModelMap model){
        Team team = ts.getOne(teamName);
        if (team != null) {
            Dt dt = dtr.findByTeam(teamName);
            model.put("dt", dt);
        }
        model.put("playerList", pls.listByTeamName(teamName));
        model.put("team", team);
        return "teams/team";
    }
}
