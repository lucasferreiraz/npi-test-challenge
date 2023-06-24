package com.challenge.npi.dtos;

import com.challenge.npi.entities.Socio;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class SocioDTO {
    
    private Long id;

    @NotBlank
    @Size(min = 5, max = 50, message = "Nome deve ter entre 5 e 50 caracteres")
    private String nome;

    @NotNull
    @PositiveOrZero(message = "O valor da renda deve ser maior ou igual a zero.")
    private Double renda;

    private Boolean ativo;
    
    public SocioDTO() {
    }

    public SocioDTO(Long id, String nome, Double renda, Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.renda = renda;
        this.ativo = ativo;
    }

    public SocioDTO(Socio socio) {
        this.id = socio.getId();
        this.nome = socio.getNome();
        this.renda = socio.getRenda();
        this.ativo = socio.getAtivo();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getRenda() {
        return renda;
    }

    public void setRenda(Double renda) {
        this.renda = renda;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
