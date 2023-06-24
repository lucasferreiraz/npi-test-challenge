package com.challenge.npi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.challenge.npi.dtos.DependenteDTO;
import com.challenge.npi.entities.Dependente;
import com.challenge.npi.repositories.DependenteRepository;
import com.challenge.npi.services.exceptions.DatabaseException;
import com.challenge.npi.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DependenteService {
    
    @Autowired
    private DependenteRepository dependenteRepository;

    @Transactional(readOnly = true)
    public Page<DependenteDTO> findAllPaged(Pageable pageable) {
        Page<Dependente> list = dependenteRepository.findAll(pageable);
        return list.map(dependente -> new DependenteDTO(dependente));
    }

    @Transactional(readOnly = true)
    public DependenteDTO findById(Long id) {
        Optional<Dependente> optional = dependenteRepository.findById(id);
        Dependente dependente = optional.orElseThrow(() -> new ResourceNotFoundException("Resource not found."));
        return new DependenteDTO(dependente);
    }

    @Transactional
    public DependenteDTO insert(DependenteDTO dto) {
        Dependente dependente = new Dependente();
        copyDtoToEntity(dto, dependente);

        dependente = dependenteRepository.save(dependente);
        return new DependenteDTO(dependente);
    }

    @Transactional
    public DependenteDTO update(Long id, DependenteDTO dto) {
        try {
            Dependente dependente = dependenteRepository.getReferenceById(id);
            copyDtoToEntity(dto, dependente);

            dependente = dependenteRepository.save(dependente);
            return new DependenteDTO(dependente);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found: " + id);
        }

    }

    public void delete(Long id) {
        try {
            dependenteRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found: " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity Violation");
        }
    }

    private void copyDtoToEntity(DependenteDTO dto, Dependente entity) {
        entity.setNome(dto.getNome());
        entity.setIdade(dto.getIdade());
    }

}
