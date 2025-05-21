package com.example.tcc3.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.content.Context;

import com.example.tcc3.interfaces.AlunoDao;
import com.example.tcc3.interfaces.ProfessorDao;
import com.example.tcc3.model.Aluno;
import com.example.tcc3.model.Professor;

@Database(entities = {Professor.class, Aluno.class}, version = 2)
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

                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    com.example.tcc3.database.AppDatabase.class, "app_database")
                            .addMigrations(MIGRATION_1_2)  // Adiciona a migração
                            .build();
                }
            }
        }
        return INSTANCE;
    }
            // Adicione esta migração vazia (se não houve mudanças estruturais)
    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
            @Override
            public void migrate(SupportSQLiteDatabase database) {
                // Não faz nada pois não houve alterações estruturais
            }
    };

}

