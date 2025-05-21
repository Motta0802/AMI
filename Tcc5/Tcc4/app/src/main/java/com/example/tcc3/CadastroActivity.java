package com.example.tcc3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tcc3.database.AppDatabase;
import com.example.tcc3.databinding.ActivityCadastroBinding;
import com.example.tcc3.model.Aluno;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CadastroActivity extends AppCompatActivity {

    private ActivityCadastroBinding binding;
    private AppDatabase db;
    private ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = AppDatabase.getDatabase(this);
        executorService = Executors.newSingleThreadExecutor();

        binding.btnCadastrarAluno.setOnClickListener(v -> {
            String nome = binding.etNomeAluno.getText().toString().trim();
            String email = binding.etEmailAluno.getText().toString().trim();
            String senha = binding.etSenhaAluno.getText().toString().trim();
            String matStr = binding.etMatAluno.getText().toString().trim();

            if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || matStr.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }

            int mat;
            try {
                mat = Integer.parseInt(matStr);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Matrícula inválida", Toast.LENGTH_SHORT).show();
                return;
            }

            Aluno novoAluno = new Aluno(nome, email, senha);

            executorService.execute(() -> {
                db.alunoDao().insert(novoAluno);
                runOnUiThread(() -> {
                    Toast.makeText(this, "Aluno cadastrado com sucesso!", Toast.LENGTH_SHORT).show();

                    // Redireciona para a tela de login
                    Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish(); // Finaliza a tela de cadastro
                });
            });

        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }
}
