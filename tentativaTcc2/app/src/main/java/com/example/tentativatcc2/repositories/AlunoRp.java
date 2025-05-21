package com.example.tentativatcc2.repositories;

import android.app.Application;
import androidx.lifecycle.LiveData;

import com.example.tentativatcc2.databases.AlunoDb;
import com.example.tentativatcc2.interfaces.AlunoDAO;
import com.example.tentativatcc2.models.Aluno;

import java.util.List;

public class AlunoRp {

    private AlunoDAO alunoDao;
    private LiveData<List<Aluno>> allAlunos;

    public AlunoRp(Application application) {
        AlunoDb db = AlunoDb.getDatabase(application);
        alunoDao = db.alunoDao();
        allAlunos = alunoDao.getAllAlunos();
    }

    public LiveData<List<Aluno>> getAllAlunos() {
        return allAlunos;
    }

    public void insert(Aluno aluno) {
        AlunoDb.databaseWriteExecutor.execute(() -> {
            alunoDao.insert(aluno);
        });
    }

    public void deleteAll() {
        AlunoDb.databaseWriteExecutor.execute(() -> {
            alunoDao.deleteAll();
        });
    }

    public void deleteById(int id) {
        AlunoDb.databaseWriteExecutor.execute(() -> {
            alunoDao.deleteById(id);
        });
    }

    public void update(int id, String nome, int idade, String curso) {
        AlunoDb.databaseWriteExecutor.execute(() -> {
            alunoDao.update(id, nome, idade, curso);
        });
    }
}
