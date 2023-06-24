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

import com.challenge.npi.dtos.SocioDTO;
import com.challenge.npi.services.SocioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/socio")
public class SocioController {

    @Autowired
    private SocioService socioService;

    @GetMapping
    public ResponseEntity<Page<SocioDTO>> findAll(Pageable pageable) {
        Page<SocioDTO> list = socioService.findAllPaged(pageable);
        return new ResponseEntity<Page<SocioDTO>>(list, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SocioDTO> findById(@PathVariable Long id) {
        SocioDTO socioDTO = socioService.findById(id);
        return new ResponseEntity<>(socioDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SocioDTO> insert(@Valid @RequestBody SocioDTO dto) {
        dto = socioService.insert(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<SocioDTO> update(@Valid @PathVariable Long id, @RequestBody SocioDTO dto) {
        dto = socioService.update(id, dto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        socioService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
