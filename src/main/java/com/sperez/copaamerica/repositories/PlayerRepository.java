package com.sperez.copaamerica.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.sperez.copaamerica.entities.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, String> {

    @Query("SELECT p FROM Player p WHERE p.surname = :name")
    public Player findByName(@Param("name") String name);

    @Query("SELECT p FROM Player p WHERE p.team.name = :teamName")
    public List<Player> findByTeam(@Param("teamName") String teamName);
}
