package com.example.tcc3.interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tcc3.model.Professor;

import java.util.List;

@Dao
public interface ProfessorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Professor professor);

    @Update
    void update(Professor professor);

    @Delete
    void delete(Professor professor);

    @Query("SELECT * FROM professores")
    LiveData<List<Professor>> getAllProfessores();
    @Query("SELECT * FROM professores WHERE email = :email AND senha = :senha")
    Professor login(String email, String senha);

    @Query("SELECT * FROM professores WHERE id = :id")
    Professor getProfessorById(int id);

    }



