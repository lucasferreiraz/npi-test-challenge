package com.challenge.npi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.npi.entities.Dependente;

public interface DependenteRepository extends JpaRepository<Dependente, Long> {
    
}
