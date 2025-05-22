package com.example.tcc3.editar;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tcc3.database.AppDatabase;
import com.example.tcc3.databinding.ActivityEditarAlunoBinding;
import com.example.tcc3.model.Aluno;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EditarAlunoActivity extends AppCompatActivity {

    private ActivityEditarAlunoBinding binding;
    private AppDatabase db;
    private ExecutorService executor;
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditarAlunoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = AppDatabase.getDatabase(this);
        executor = Executors.newSingleThreadExecutor();

        int alunoId = getIntent().getIntExtra("ALUNO_ID", -1);
        carregarAluno(alunoId);

        binding.btnSalvar.setOnClickListener(v -> salvarAlteracoes());
        binding.btnVoltar.setOnClickListener(v -> finish());  // Botão voltar
    }

    private void carregarAluno(int id) {
        executor.execute(() -> {
            aluno = db.alunoDao().getAlunoById(id);
            runOnUiThread(() -> {
                if (aluno != null) {
                    binding.etNome.setText(aluno.nome);
                    binding.etEmail.setText(aluno.email);
                    binding.etSenha.setText(aluno.senha);
                }
            });
        });
    }

    private void salvarAlteracoes() {
        String novoNome = binding.etNome.getText().toString().trim();
        String novoEmail = binding.etEmail.getText().toString().trim();
        String novaSenha = binding.etSenha.getText().toString().trim();

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executor.shutdown();
    }
}
