package com.example.tcc3.editar;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tcc3.R;
import com.example.tcc3.database.AppDatabase;
import com.example.tcc3.model.Professor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EditarProfessorActivity extends AppCompatActivity {
    private EditText etNome, etEmail;
    private AppDatabase db;
    private ExecutorService executor;
    private Professor professor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_professor);

        db = AppDatabase.getDatabase(this);
        executor = Executors.newSingleThreadExecutor();

        etNome = findViewById(R.id.etNome);
        etEmail = findViewById(R.id.etEmail);

        int professorId = getIntent().getIntExtra("PROFESSOR_ID", -1);
        carregarProfessor(professorId);

        findViewById(R.id.btnSalvar).setOnClickListener(v -> salvarAlteracoes());
    }

    private void carregarProfessor(int id) {
        executor.execute(() -> {
            professor = db.professorDao().getProfessorById(id);
            runOnUiThread(() -> {
                if (professor != null) {
                    etNome.setText(professor.nome);
                    etEmail.setText(professor.email);
                }
            });
        });
    }

    private void salvarAlteracoes() {
        String novoNome = etNome.getText().toString();
        String novoEmail = etEmail.getText().toString();

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
}