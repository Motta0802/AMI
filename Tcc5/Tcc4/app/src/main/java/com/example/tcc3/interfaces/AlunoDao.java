package com.example.tcc3.interfaces;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tcc3.model.Aluno;


import java.util.List;

@Dao
public interface AlunoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Aluno aluno);
    @Update
    void update(Aluno aluno);

    @Delete
    void delete(Aluno aluno);

    @Query("SELECT * FROM alunos")
    LiveData<List<Aluno>> getAllAlunos();

    @Query("SELECT * FROM alunos WHERE matricula = :id")
    Aluno getAlunoById(int id);
    @Query("SELECT * FROM alunos WHERE email = :email AND senha = :senha")
    Aluno login(String email, String senha);
    
}
