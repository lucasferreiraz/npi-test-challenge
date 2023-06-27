package com.challenge.npi.dtos;

import com.challenge.npi.entities.Dependente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class DependenteDTO {

    private Long id;

    @NotBlank
    @Size(min = 5, max = 50, message = "Nome deve ter entre 5 e 50 caracteres")
    private String nome;

    @NotNull
    @PositiveOrZero(message = "O valor da idade deve ser maior ou igual a zero.")
    private Integer idade;
    
    public DependenteDTO() {
    }

    public DependenteDTO(Long id, String nome, Integer idade) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
    }

    public DependenteDTO(Dependente dto) {
        this.id = dto.getId();
        this.nome = dto.getNome();
        this.idade = dto.getIdade();
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

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Dependente toEntity() {
        return new Dependente(this.id, this.nome, this.idade);
    }
}
