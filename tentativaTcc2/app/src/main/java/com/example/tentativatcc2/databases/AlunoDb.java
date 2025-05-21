package com.example.tentativatcc2.databases;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.example.tentativatcc2.interfaces.AlunoDAO;
import com.example.tentativatcc2.models.Aluno;

import java.util.concurrent.Executor;

@Database(entities = {Aluno.class}, version = 1, exportSchema = false)
public abstract class AlunoDb extends RoomDatabase {

    public static Executor databaseWriteExecutor;

    public abstract AlunoDAO alunoDao();

    private static volatile AlunoDb INSTANCE;

    public static AlunoDb getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AlunoDb.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AlunoDb.class, "aluno_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
