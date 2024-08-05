package com.sperez.copaamerica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.sperez.copaamerica.entities.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer>{

    @Query("SELECT t FROM Team t WHERE t.name = :name")
    public Team findByName(@Param("name") String name);
}
