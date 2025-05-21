package com.example.tentativatcc2.interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.tentativatcc2.models.Aluno;

import java.util.List;

@Dao
public interface AlunoDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Aluno aluno);

    @Query("SELECT * FROM aluno_table ORDER BY nome ASC")
    LiveData<List<Aluno>> getAllAlunos();

    @Query("DELETE FROM aluno_table")
    void deleteAll();

    @Query("DELETE FROM aluno_table WHERE id = :id")
    void deleteById(int id);

    @Query("UPDATE aluno_table SET nome = :nome, idade = :idade, curso = :curso WHERE id = :id")
    void update(int id, String nome, int idade, String curso);
}
