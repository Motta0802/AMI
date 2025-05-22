package com.example.tcc3.editar;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tcc3.database.AppDatabase;
import com.example.tcc3.databinding.ActivityEditarProfessorBinding;
import com.example.tcc3.model.Professor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EditarProfessorActivity extends AppCompatActivity {

    private ActivityEditarProfessorBinding binding;
    private AppDatabase db;
    private ExecutorService executor;
    private Professor professor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditarProfessorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = AppDatabase.getDatabase(this);
        executor = Executors.newSingleThreadExecutor();

        int professorId = getIntent().getIntExtra("PROFESSOR_ID", -1);
        carregarProfessor(professorId);

        binding.btnSalvar.setOnClickListener(v -> salvarAlteracoes());
        binding.btnVoltar.setOnClickListener(v -> finish());  // Botão voltar
    }

    private void carregarProfessor(int id) {
        executor.execute(() -> {
            professor = db.professorDao().getProfessorById(id);
            runOnUiThread(() -> {
                if (professor != null) {
                    binding.etNome.setText(professor.nome);
                    binding.etEmail.setText(professor.email);
                }
            });
        });
    }

    private void salvarAlteracoes() {
        String novoNome = binding.etNome.getText().toString().trim();
        String novoEmail = binding.etEmail.getText().toString().trim();

        if (novoNome.isEmpty() || novoEmail.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        executor.execute(() -> {
            professor.nome = novoNome;
            professor.email = novoEmail;
            db.professorDao().update(professor);
            runOnUiThread(() -> {
                Toast.makeText(this, "Professor atualizado!", Toast.LENGTH_SHORT).show();
                finish();
            });
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executor.shutdown();
    }
}
