package com.example.mycrud;

import java.io.Serializable;

public class Aluno implements Serializable {
    private Integer id;
    private String nome,cpf,telefone,cep;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCep() { return cep; }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
