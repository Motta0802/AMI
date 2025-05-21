package com.example.tcc3.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.regex.Pattern;

@Entity(tableName = "alunos")
public class Aluno {
    @PrimaryKey(autoGenerate = true)
    public int matricula;
    public String nome;
    public String email;
    public String senha;
    private static final String PASSWORD_REGEX =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";
    private static final Pattern PATTERN = Pattern.compile(PASSWORD_REGEX);
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

    public Aluno(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;

    }
}