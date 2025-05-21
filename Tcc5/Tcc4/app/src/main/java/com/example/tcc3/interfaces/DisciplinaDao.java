package com.example.tcc3.interfaces;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tcc3.model.Disciplina;

import java.util.List;

@Dao
public interface DisciplinaDao {

    @Insert
    void insert(Disciplina disciplina);

    @Update
    void update(Disciplina disciplina);

    @Delete
    void delete(Disciplina disciplina);

    @Query("SELECT * FROM Disciplina")
    List<Disciplina> getAll();

    @Query("SELECT * FROM Disciplina WHERE id = :id")
    Disciplina getById(int id);
}
