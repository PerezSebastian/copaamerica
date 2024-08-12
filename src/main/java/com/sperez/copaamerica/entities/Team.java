package com.sperez.copaamerica.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String shortName;
    private String aka;
    private Integer debut;
    private Integer appearances;
    private String history;
    private String trophies;
    private String maxStriker;
    @OneToOne
    private Photo flag;
    @OneToOne
    private Photo teamphoto;
    @OneToOne
    private Photo pincelflag;

    public String getAka() {
        return aka;
    }

    public void setAka(String aka) {
        this.aka = aka;
    }

    public Integer getDebut() {
        return debut;
    }

    public void setDebut(Integer debut) {
        this.debut = debut;
    }

    public Integer getAppearances() {
        return appearances;
    }

    public void setAppearances(Integer appearances) {
        this.appearances = appearances;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getTrophies() {
        return trophies;
    }

    public void setTrophies(String trophies) {
        this.trophies = trophies;
    }

    public String getMaxStriker() {
        return maxStriker;
    }

    public void setMaxStriker(String maxStriker) {
        this.maxStriker = maxStriker;
    }

    public Photo getFlag() {
        return flag;
    }

    public void setFlag(Photo flag) {
        this.flag = flag;
    }

    public Photo getTeamphoto() {
        return teamphoto;
    }

    public void setTeamphoto(Photo teamphoto) {
        this.teamphoto = teamphoto;
    }

    public Team() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Photo getPincelflag() {
        return pincelflag;
    }

    public void setPincelflag(Photo pincelflag) {
        this.pincelflag = pincelflag;
    }

}
