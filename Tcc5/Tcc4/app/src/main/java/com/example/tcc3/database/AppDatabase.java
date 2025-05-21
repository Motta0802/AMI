package com.example.tcc3.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.content.Context;

import com.example.tcc3.interfaces.AlunoDao;
import com.example.tcc3.interfaces.DisciplinaDao;
import com.example.tcc3.interfaces.ProfessorDao;
import com.example.tcc3.model.Aluno;
import com.example.tcc3.model.Disciplina;
import com.example.tcc3.model.Professor;

@Database(entities = {Professor.class, Aluno.class, Disciplina.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ProfessorDao professorDao();
    public abstract AlunoDao alunoDao();
    public abstract DisciplinaDao disciplinaDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "app_database")
                            .addMigrations(MIGRATION_1_2, MIGRATION_2_3) // Adicione todas as migrações necessárias
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    // Migração de 1 para 2 (vazia, se não houve alterações)
    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Nenhuma alteração estrutural
        }
    };

    // Migração de 2 para 3 (adicione aqui o que mudou na versão 3)
    private static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {

                database.execSQL("CREATE TABLE IF NOT EXISTS `Disciplina` (" +
                        "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                        "`nome` TEXT, " +
                        "`descricao` TEXT, " +
                        "`cargaHoraria` INTEGER NOT NULL)");
            }
        };

}


