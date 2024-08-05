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

import com.sperez.copaamerica.entities.Dt;
import com.sperez.copaamerica.entities.Team;
import com.sperez.copaamerica.exceptions.MiException;
import com.sperez.copaamerica.services.DtService;
import com.sperez.copaamerica.services.TeamService;

@Controller
@RequestMapping("/dt")
public class DtController {

    @Autowired
    private DtService dts;

    @Autowired
    private TeamService ts;

    @GetMapping("/registrar")
    public String registrar(ModelMap model) {
        List<Team> teamList = new ArrayList<>();
        List<Dt> dtList = new ArrayList<>();
        teamList = ts.listAll();
        dtList = dts.listAll();
        model.put("teamList", teamList);
        model.put("dtList", dtList);
        return "dt/createDt";
    }

    @PostMapping("/guardar")
    public String guardar(@RequestParam String name, @RequestParam String surname,
            @RequestParam(required = false) Integer idTeam, @RequestParam MultipartFile file, ModelMap model) {
        try {
            dts.createDt(name, surname, idTeam, file);
            model.put("exito", "Director tecnico cargado exitosamente!");
        } catch (MiException ex) {
            System.out.println(ex.getMessage());
            model.put("error", ex.getMessage());
        }
        List<Team> teamList = new ArrayList<>();
        List<Dt> dtList = new ArrayList<>();
        teamList = ts.listAll();
        dtList = dts.listAll();
        model.put("teamList", teamList);
        model.put("dtList", dtList);
        return "dt/createDt";
    }
}
