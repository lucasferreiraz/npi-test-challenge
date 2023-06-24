package com.challenge.npi.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.challenge.npi.dtos.DependenteDTO;
import com.challenge.npi.services.DependenteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/dependente")
public class DependenteController {
    
    @Autowired
    private DependenteService dependenteService;

    @GetMapping
    public ResponseEntity<Page<DependenteDTO>> findAll(Pageable pageable) {
        Page<DependenteDTO> list = dependenteService.findAllPaged(pageable);
        return new ResponseEntity<Page<DependenteDTO>>(list, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DependenteDTO> findById(@PathVariable Long id) {
        DependenteDTO dependenteDTO = dependenteService.findById(id);
        return new ResponseEntity<>(dependenteDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DependenteDTO> insert(@Valid @RequestBody DependenteDTO dto) {
        dto = dependenteService.insert(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<DependenteDTO> update(@Valid @PathVariable Long id, @RequestBody DependenteDTO dto) {
        dto = dependenteService.update(id, dto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        dependenteService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
