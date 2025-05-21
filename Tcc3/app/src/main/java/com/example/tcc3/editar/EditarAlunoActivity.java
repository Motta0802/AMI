package com.example.tcc3.editar;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tcc3.R;
import com.example.tcc3.database.AppDatabase;
import com.example.tcc3.model.Aluno;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EditarAlunoActivity extends AppCompatActivity {

    private EditText etNome, etEmail, etSenha;
    private AppDatabase db;
    private ExecutorService executor;
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_aluno);

        db = AppDatabase.getDatabase(this);
        executor = Executors.newSingleThreadExecutor();

        etNome = findViewById(R.id.etNome);
        etEmail = findViewById(R.id.etEmail);


        int alunoId = getIntent().getIntExtra("ALUNO_ID", -1);
        carregarAluno(alunoId);

        findViewById(R.id.btnSalvar).setOnClickListener(v -> salvarAlteracoes());
    }

    private void carregarAluno(int id) {
        executor.execute(() -> {
            aluno = db.alunoDao().getAlunoById(id);
            runOnUiThread(() -> {
                if (aluno != null) {
                    etNome.setText(aluno.nome);
                    etEmail.setText(aluno.email);
                    etSenha.setText(aluno.senha);
                }
            });
        });
    }

    private void salvarAlteracoes() {
        String novoNome = etNome.getText().toString();
        String novoEmail = etEmail.getText().toString();
        String novaSenha = etSenha.getText().toString();

        if (novoNome.isEmpty() || novoEmail.isEmpty() || novaSenha.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        executor.execute(() -> {
            aluno.nome = novoNome;
            aluno.email = novoEmail;
            aluno.senha = novaSenha;
            db.alunoDao().update(aluno);
            runOnUiThread(() -> {
                Toast.makeText(this, "Aluno atualizado!", Toast.LENGTH_SHORT).show();
                finish();
            });
        });
    }
}