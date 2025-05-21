package com.example.tcc3.lista;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tcc3.database.AppDatabase;
import com.example.tcc3.editar.EditarProfessorActivity;
import com.example.tcc3.model.Professor;
import com.example.tcc3.ui.ProfessorAdapter;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

public class ListaProfessoresActivity extends AppCompatActivity {
    private ProfessorAdapter adapter;
    private AppDatabase db;
    private ExecutorService executor;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        configurarRecyclerView();
        observarProfessores();
    }

    private void configurarRecyclerView() {
        adapter = new ProfessorAdapter(new ArrayList<>(), professor -> {
            // Abrir diálogo de opções (Editar/Excluir)
            showOptionsDialog(professor);
        });
        recyclerView.setAdapter(adapter);
    }

    // ============ OPERAÇÕES CRUD ============

    // CONSULTAR (Já implementado via LiveData)
    private void observarProfessores() {
        db.professorDao().getAllProfessores().observe(this, professores -> {
            adapter.setProfessores(professores);
        });
    }

    // EDITAR
    private void editarProfessor(Professor professor) {
        Intent intent = new Intent(this, EditarProfessorActivity.class);
        intent.putExtra("PROFESSOR_ID", professor.id);
        startActivity(intent);
    }

    // DELETAR
    private void deletarProfessor(Professor professor) {
        new AlertDialog.Builder(this)
                .setTitle("Confirmar exclusão")
                .setMessage("Deseja excluir o professor " + professor.nome + "?")
                .setPositiveButton("Excluir", (dialog, which) -> {
                    executor.execute(() -> {
                        db.professorDao().delete(professor);
                    });
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    // Diálogo de opções
    private void showOptionsDialog(Professor professor) {
        String[] options = {"Editar", "Excluir"};
        new AlertDialog.Builder(this)
                .setTitle(professor.nome)
                .setItems(options, (dialog, which) -> {
                    if (which == 0) editarProfessor(professor);
                    else if (which == 1) deletarProfessor(professor);
                })
                .show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executor.shutdown();
    }
}