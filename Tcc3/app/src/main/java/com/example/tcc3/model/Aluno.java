package com.example.tcc3.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "alunos")
public class Aluno {
    @PrimaryKey
    public int matricula;
    public String nome;
    public String email;
    public String senha;

    public Aluno(int matricula, String nome, String email, String senha) {
        this.matricula = matricula;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }
}