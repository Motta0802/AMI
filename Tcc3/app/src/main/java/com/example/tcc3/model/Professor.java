package com.example.tcc3.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "professores")
public class Professor {
    @PrimaryKey
    public int id;
    public String nome;
    public String email;
    public String senha;

    public Professor(int id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }
}