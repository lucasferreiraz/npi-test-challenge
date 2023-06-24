package com.challenge.npi.dtos;

import com.challenge.npi.entities.Dependente;

public class DependenteDTO {

    private Long id;
    private String nome;
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

    
}
