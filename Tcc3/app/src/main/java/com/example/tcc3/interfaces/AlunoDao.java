package com.example.tcc3.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.tcc3.model.Aluno;

@Dao
public interface AlunoDao {
    @Insert
    void insert(Aluno aluno);

    @Query("SELECT * FROM alunos WHERE email = :email AND senha = :senha")
    Aluno login(String email, String senha);
}
