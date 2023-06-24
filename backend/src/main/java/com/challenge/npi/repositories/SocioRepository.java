package com.challenge.npi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.npi.entities.Socio;

public interface SocioRepository extends JpaRepository<Socio, Long> {
    
}
