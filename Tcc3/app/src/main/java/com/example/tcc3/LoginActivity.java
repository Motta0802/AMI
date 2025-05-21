package com.example.tcc3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tcc3.database.AppDatabase;
import com.example.tcc3.model.Aluno;
import com.example.tcc3.model.Professor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etSenha;
    private Button btnLoginProfessor, btnLoginAluno;
    private AppDatabase db;
    private ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etSenha = findViewById(R.id.etSenha);
        btnLoginProfessor = findViewById(R.id.btnLoginProfessor);
        btnLoginAluno = findViewById(R.id.btnLoginAluno);

        db = AppDatabase.getDatabase(this);
        executorService = Executors.newSingleThreadExecutor();

        // Insere um professor de teste
        executorService.execute(() -> {
            Log.d("LoginActivity", "Inserindo professor de teste");
            db.professorDao().insert(new Professor("briviu", "professor@teste.com", "senha123"));
        });

            //Botão de login do professor
        btnLoginProfessor.setOnClickListener(v -> {
            String email = etEmail.getText().toString();
            String senha = etSenha.getText().toString();
            loginProfessor(email, senha);
        });

        // Insere um professor de teste
        executorService.execute(() -> {
            Log.d("LoginActivity", "Inserindo aluno de teste");
            db.alunoDao().insert(new Aluno(1234, "thiagus", "aluno@teste.com", "1234"));
        });

        //Botão de login do aluno
        btnLoginAluno.setOnClickListener(v -> {
            String email = etEmail.getText().toString();
            String senha = etSenha.getText().toString();
            loginAluno(email, senha);
        });
    }

    private void loginProfessor(String email, String senha) {
        executorService.execute(() -> {
            Log.d("LoginActivity", "Tentando fazer login do professor");
            Professor professor = db.professorDao().login(email, senha);
            runOnUiThread(() -> {
                //Se o campo professor estiver vazio o login será aceito, senão o login irá falhar
                if (professor != null) {
                    Log.d("LoginActivity", "Login do professor bem-sucedido");
                    startActivity(new Intent(LoginActivity.this, ProfessorActivity.class));
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
                //Se o campo aluno estiver vazio o login será aceito, senão o login irá falhar
                if (aluno != null) {
                    Log.d("LoginActivity", "Login do aluno bem-sucedido");
                    startActivity(new Intent(LoginActivity.this, AlunoActivity.class));
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