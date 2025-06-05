package com.example.tcc3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tcc3.database.AppDatabase;
import com.example.tcc3.databinding.ActivityLoginBinding;
import com.example.tcc3.model.Aluno;
import com.example.tcc3.model.Professor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private AppDatabase db;
    private ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnIrParaCadastro.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
            startActivity(intent);
        });

        db = AppDatabase.getDatabase(this);
        executorService = Executors.newSingleThreadExecutor();

        // Inserir professor de teste
        executorService.execute(() -> {
            Log.d("LoginActivity", "Inserindo professor de teste");
            db.professorDao().insert(new Professor("briviu", "professor@teste.com", "senha123"));
        });

        // Inserir aluno de teste
        executorService.execute(() -> {
            Log.d("LoginActivity", "Inserindo aluno de teste");
            db.alunoDao().insert(new Aluno("thiago", "aluno@teste.com", "1234"));
        });

        // Botão de login do professor
        binding.btnLoginProfessor.setOnClickListener(v -> {
            String email = binding.etEmail.getText().toString();
            String senha = binding.etSenha.getText().toString();
            loginProfessor(email, senha);
        });

        // Botão de login do aluno
        binding.btnLoginAluno.setOnClickListener(v -> {
            String email = binding.etEmail.getText().toString();
            String senha = binding.etSenha.getText().toString();
            loginAluno(email, senha);
        });
    }

    private void loginProfessor(String email, String senha) {
        executorService.execute(() -> {
            Log.d("LoginActivity", "Tentando fazer login do professor");
            Professor professor = db.professorDao().login(email, senha);
            runOnUiThread(() -> {
                if (professor != null) {
                    Log.d("LoginActivity", "Login do professor bem-sucedido");
                    Intent intent = new Intent(LoginActivity.this, ProfessorActivity.class);
                    intent.putExtra("email", email);  // passando o email para a próxima activity
                    startActivity(intent);
                } else {
                    Log.d("LoginActivity", "Login do professor falhou");
                    Toast.makeText(LoginActivity.this, "Login falhou", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private void loginAluno(String email, String senha) {
        executorService.execute(() -> {
            Log.d("LoginActivity", "Tentando fazer login do aluno");
            Aluno aluno = db.alunoDao().login(email, senha);
            runOnUiThread(() -> {
                if (aluno != null) {
                    Log.d("LoginActivity", "Login do aluno bem-sucedido");
                    Intent intent = new Intent(LoginActivity.this, AlunoActivity.class);
                    intent.putExtra("email", email);  // passando o email para a próxima activity
                    startActivity(intent);
                } else {
                    Log.d("LoginActivity", "Login do aluno falhou");
                    Toast.makeText(LoginActivity.this, "Login falhou", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }
}
