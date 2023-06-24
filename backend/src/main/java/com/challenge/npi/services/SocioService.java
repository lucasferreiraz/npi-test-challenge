package com.challenge.npi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.challenge.npi.dtos.SocioDTO;
import com.challenge.npi.entities.Socio;
import com.challenge.npi.repositories.SocioRepository;

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
        return new SocioDTO(optional.get());
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
        Socio socio = socioRepository.getReferenceById(id);
        copyDtoToEntity(dto, socio);

        socio = socioRepository.save(socio);
        return new SocioDTO(socio);
    }

    public void delete(Long id) {
        socioRepository.deleteById(id);
    }

    private void copyDtoToEntity(SocioDTO dto, Socio entity) {
        entity.setNome(dto.getNome());
        entity.setRenda(dto.getRenda());
        entity.setAtivo(dto.getAtivo());
    }

}
