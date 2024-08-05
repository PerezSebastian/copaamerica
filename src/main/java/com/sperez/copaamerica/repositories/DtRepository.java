package com.sperez.copaamerica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sperez.copaamerica.entities.Dt;

@Repository
public interface DtRepository extends JpaRepository<Dt, String>{

    @Query("SELECT dt FROM Dt dt WHERE dt.surname = :name")
    public Dt findByName(@Param("name") String name);

    @Query("SELECT dt FROM Dt dt WHERE dt.team.name = :name")
    public Dt findByTeam(@Param("name") String name);
}
