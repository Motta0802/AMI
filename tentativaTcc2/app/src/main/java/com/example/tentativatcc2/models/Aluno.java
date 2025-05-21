package com.example.tentativatcc2.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "aluno_table")
public class Aluno {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String nome;
    private int idade;
    private String curso;
    private String matricula;
    private String email;

    public Aluno(String nome, int idade, String curso, String matricula, String email) {
        this.nome = nome;
        this.idade = idade;
        this.curso = curso;
        this.matricula = matricula;
        this.email = email;
    }

    // Getters e Setters
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

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}