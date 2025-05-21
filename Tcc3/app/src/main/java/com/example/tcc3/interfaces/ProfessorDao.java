package com.example.tcc3.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.tcc3.model.Professor;

@Dao
public interface ProfessorDao {
    @Insert
    void insert(Professor professor);

    @Query("SELECT * FROM professores WHERE email = :email AND senha = :senha")
    Professor login(String email, String senha);
}