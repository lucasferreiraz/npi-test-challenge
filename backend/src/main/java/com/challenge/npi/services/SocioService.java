package com.challenge.npi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.challenge.npi.dtos.SocioDTO;
import com.challenge.npi.entities.Socio;
import com.challenge.npi.repositories.SocioRepository;
import com.challenge.npi.services.exceptions.DatabaseException;
import com.challenge.npi.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SocioService {

    @Autowired
    private SocioRepository socioRepository;

    @Transactional(readOnly = true)
    public Page<SocioDTO> findAllPaged(Pageable pageable) {
        Page<Socio> list = socioRepository.findAll(pageable);
        return list.map(socio -> new SocioDTO(socio));
    }

    @Transactional(readOnly = true)
    public SocioDTO findById(Long id) {
        Optional<Socio> optional = socioRepository.findById(id);
        Socio socio = optional.orElseThrow(() -> new ResourceNotFoundException("Resource not found."));
        return new SocioDTO(socio);
    }

    @Transactional
    public SocioDTO insert(SocioDTO dto) {
        Socio socio = new Socio();
        copyDtoToEntity(dto, socio);

        socio = socioRepository.save(socio);
        return new SocioDTO(socio);
    }

    @Transactional
    public SocioDTO update(Long id, SocioDTO dto) {
        try {
            Socio socio = socioRepository.getReferenceById(id);
            copyDtoToEntity(dto, socio);

            socio = socioRepository.save(socio);
            return new SocioDTO(socio);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found: " + id);
        }

    }

    public void delete(Long id) {
        try {
            socioRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found: " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity Violation");
        }
    }

    private void copyDtoToEntity(SocioDTO dto, Socio entity) {
        entity.setNome(dto.getNome());
        entity.setRenda(dto.getRenda());
        entity.setAtivo(dto.getAtivo());
    }

}
