package com.challenge.npi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.challenge.npi.dtos.DependenteDTO;
import com.challenge.npi.dtos.SocioDTO;
import com.challenge.npi.entities.Dependente;
import com.challenge.npi.entities.Socio;
import com.challenge.npi.repositories.SocioRepository;
import com.challenge.npi.services.exceptions.DatabaseException;
import com.challenge.npi.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SocioService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SocioService.class.getName());

    @Autowired
    private SocioRepository socioRepository;

    @Autowired
    private DependenteService dependenteService;

    @Transactional(readOnly = true)
    public Page<SocioDTO> findAllPaged(Pageable pageable) {
        LOGGER.info("Fetching all Socio entities with pagination");

        Page<Socio> list = socioRepository.findAll(pageable);

        LOGGER.info("Fetched {} Socio entities", list.getNumberOfElements());

        return list.map(socio -> new SocioDTO(socio, socio.getDependentes()));
    }

    @Transactional(readOnly = true)
    public SocioDTO findById(Long id) {
        LOGGER.info("Fetching Socio entity by ID: {}", id);

        Optional<Socio> optional = socioRepository.findById(id);
        Socio socio = optional.orElseThrow(() -> {

            LOGGER.warn("Socio entity not found for ID: {}", id);
            return new ResourceNotFoundException("Resource not found.");
        });

        return new SocioDTO(socio, socio.getDependentes());
    }

    @Transactional
    public SocioDTO insert(SocioDTO dto) {
        LOGGER.info("Inserting new Socio entity");

        Socio socio = new Socio();
        copyDtoToEntity(dto, socio);

        socio = socioRepository.save(socio);

        if (dto.getDependentes() != null && !dto.getDependentes().isEmpty()) {
            for (DependenteDTO dependenteDTO : dto.getDependentes()) {
                Dependente dependente = new Dependente();
                copyDependenteDtoToEntity(dependenteDTO, dependente);

                dependente.setSocio(socio);

                dependenteService.insert(new DependenteDTO(dependente));

                socio.getDependentes().add(dependente);
            }
        }

        LOGGER.info("Inserted new Socio entity with ID: {}", socio.getId());
        return new SocioDTO(socio);
    }

    @Transactional
    public SocioDTO update(Long id, SocioDTO dto) {
        try {
            LOGGER.info("Updating Socio entity with ID: {}", id);

            Socio socio = socioRepository.getReferenceById(id);
            copyDtoToEntity(dto, socio);

            if (dto.getDependentes() != null && !dto.getDependentes().isEmpty()) {
                List<DependenteDTO> dependentesAtualizados = new ArrayList<>();
                for (DependenteDTO dependenteDTO : dto.getDependentes()) {
                    if (dependenteDTO.getId() != null) {
                        // Dependente existente, chamar o serviço DependenteService para atualizar
                        DependenteDTO dependenteAtualizado = dependenteService.update(dependenteDTO.getId(), dependenteDTO);
                        dependentesAtualizados.add(dependenteAtualizado);
                    } else {
                        // Novo dependente, chamar o serviço DependenteService para inserir
                        DependenteDTO dependenteInserido = dependenteService.insert(dependenteDTO);
                        dependentesAtualizados.add(dependenteInserido);
                    }
                }
                dto.setDependentes(dependentesAtualizados);
            }


            socio = socioRepository.save(socio);

            LOGGER.info("Updated Socio entity with ID: {}", socio.getId());
            return new SocioDTO(socio);

        } catch (EntityNotFoundException e) {
            LOGGER.warn("Socio entity not found for ID: {}", id);
            throw new ResourceNotFoundException("Id not found: " + id);
        }

    }

    public void delete(Long id) {
        try {
            LOGGER.info("Deleting Socio entity with ID: {}", id);
            socioRepository.deleteById(id);
            LOGGER.info("Deleted Socio entity with ID: {}", id);

        } catch (EmptyResultDataAccessException e) {
            LOGGER.warn("Socio entity not found for ID: {}", id);
            throw new ResourceNotFoundException("Id not found: " + id);

        } catch (DataIntegrityViolationException e) {
            LOGGER.warn("Attempt to remove Socio entity with ID {} ​​related to Dependents");
            throw new DatabaseException("Integrity Violation");
        }
    }

    private void copyDtoToEntity(SocioDTO dto, Socio entity) {
        entity.setNome(dto.getNome());
        entity.setRenda(dto.getRenda());
        entity.setAtivo(dto.getAtivo());
    }

    private void copyDependenteDtoToEntity(DependenteDTO dto, Dependente entity) {
        entity.setNome(dto.getNome());
        entity.setIdade(dto.getIdade());
    }

}
