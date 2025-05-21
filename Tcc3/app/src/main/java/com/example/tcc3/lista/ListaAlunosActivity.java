package com.example.tcc3.lista;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tcc3.R;
import com.example.tcc3.database.AppDatabase;
import com.example.tcc3.editar.EditarAlunoActivity;
import com.example.tcc3.model.Aluno;
import com.example.tcc3.ui.AlunoAdapter;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ListaAlunosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AlunoAdapter adapter;
    private AppDatabase db;
    private ExecutorService executor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        configurarRecyclerView();
        observarAlunos();
    }

    private void configurarRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AlunoAdapter(new ArrayList<>(), aluno -> {
            showOptionsDialog(aluno);
        });
        recyclerView.setAdapter(adapter);
    }

    private void observarAlunos() {
        db.alunoDao().getAllAlunos().observe(this, alunos -> {
            adapter.setAlunos(alunos);
        });
    }

    // ============ OPERAÇÕES CRUD ============
    private void editarAluno(Aluno aluno) {
        Intent intent = new Intent(this, EditarAlunoActivity.class);
        intent.putExtra("ALUNO_ID", aluno.matricula);
        startActivity(intent);
    }

    private void deletarAluno(Aluno aluno) {
        new AlertDialog.Builder(this)
                .setTitle("Confirmar exclusão")
                .setMessage("Deseja excluir o aluno " + aluno.nome + "?")
                .setPositiveButton("Excluir", (dialog, which) -> {
                    executor.execute(() -> {
                        db.alunoDao().delete(aluno);
                    });
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    private void showOptionsDialog(Aluno aluno) {
        String[] options = {"Editar", "Excluir"};
        new AlertDialog.Builder(this)
                .setTitle(aluno.nome)
                .setItems(options, (dialog, which) -> {
                    if (which == 0) editarAluno(aluno);
                    else if (which == 1) deletarAluno(aluno);
                })
                .show();
    }
}