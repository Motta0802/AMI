package com.example.tcc3.lista;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.tcc3.AlunoActivity;
import com.example.tcc3.DisciplinaAlunoActivity;
import com.example.tcc3.DisciplinaProfessorActivity;
import com.example.tcc3.ProfessorActivity;
import com.example.tcc3.database.AppDatabase;
import com.example.tcc3.databinding.ActivityListaAlunosBinding;
import com.example.tcc3.editar.EditarAlunoActivity;
import com.example.tcc3.model.Aluno;
import com.example.tcc3.ui.AlunoAdapter;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ListaAlunosActivity extends AppCompatActivity {

    private ActivityListaAlunosBinding binding;
    private AlunoAdapter adapter;
    private AppDatabase db;
    private ExecutorService executor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListaAlunosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = AppDatabase.getDatabase(this);
        executor = Executors.newSingleThreadExecutor();

        configurarRecyclerView();
        observarAlunos();
        binding.btnVoltar.setOnClickListener(v -> {
            Intent intent = new Intent(ListaAlunosActivity.this, ProfessorActivity.class);
            startActivity(intent);
        });

    }

    private void configurarRecyclerView() {
        binding.recyclerViewAlunos.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AlunoAdapter(new ArrayList<>(), this::showOptionsDialog);
        binding.recyclerViewAlunos.setAdapter(adapter);
    }

    private void observarAlunos() {
        db.alunoDao().getAllAlunos().observe(this, alunos -> {
            adapter.setAlunos(alunos);
        });
    }

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
                    executor.execute(() -> db.alunoDao().delete(aluno));
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





    @Override
    protected void onDestroy() {
        super.onDestroy();
        executor.shutdown();
    }
}
