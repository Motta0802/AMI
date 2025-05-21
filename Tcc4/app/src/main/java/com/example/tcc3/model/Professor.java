package com.example.tcc3.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.regex.Pattern;

@Entity(tableName = "professores")
public class Professor {
    @PrimaryKey(autoGenerate = true)  // Adicione autoGenerate
    public int id;
    public String nome;
    public String email;
    public String senha;
    private static final String PASSWORD_REGEX =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";
    private static final Pattern PATTERN = Pattern.compile(PASSWORD_REGEX);

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    // Construtor sem ID (Room irá gerar automaticamente)
    public Professor(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;

    }

}