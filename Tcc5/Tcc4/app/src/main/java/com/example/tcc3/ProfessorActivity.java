package com.example.tcc3;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tcc3.databinding.ActivityProfessorBinding;
import com.example.tcc3.editar.EditarProfessorActivity;
import com.example.tcc3.lista.ListaAlunosActivity;

public class ProfessorActivity extends AppCompatActivity {

    private ActivityProfessorBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfessorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MyApp app = (MyApp) getApplication();
        String receivedData = app.getSharedData();


        // Botão de logout
        binding.btnLogoutProfessor.setOnClickListener(v -> {
            Intent intent = new Intent(ProfessorActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        // Botão de editar
        binding.btnEditarProfessor.setOnClickListener(v -> {
            Intent intent = new Intent(ProfessorActivity.this, EditarProfessorActivity.class);
            startActivity(intent);

        });
        // Botão de visualizar alunos
        binding.btnVisualizarAlunos.setOnClickListener(v -> {
            Intent intent = new Intent(ProfessorActivity.this, ListaAlunosActivity.class);
            startActivity(intent);
        });
        binding.btnGerenciarDisciplinas.setOnClickListener(v -> {
            Intent intent = new Intent(ProfessorActivity.this, DisciplinaProfessorActivity.class);
            startActivity(intent);
        });

    }
}
