package com.example.tcc3.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.example.tcc3.interfaces.AlunoDao;
import com.example.tcc3.interfaces.ProfessorDao;
import com.example.tcc3.model.Aluno;
import com.example.tcc3.model.Professor;

@Database(entities = {Professor.class, Aluno.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ProfessorDao professorDao();
    public abstract AlunoDao alunoDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "app_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}