package com.example.tcc3.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "alunos")
public class Aluno {
    @PrimaryKey(autoGenerate = true)
    public int matricula = 0;
    public String nome;
    public String email;
    public String senha;

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Aluno(int matricula, String nome, String email, String senha) {
        this.matricula = matricula;
        this.nome = nome;
        this.email = email;
        this.senha = senha;

    }
}