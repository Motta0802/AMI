package com.example.tcc3;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tcc3.AlunoActivity;
import com.example.tcc3.ProfessorActivity;
import com.example.tcc3.R;
import com.example.tcc3.database.AppDatabase;
import com.example.tcc3.model.Aluno;
import com.example.tcc3.model.Professor;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etSenha;
    private Button btnLoginProfessor, btnLoginAluno;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etSenha = findViewById(R.id.etSenha);
        btnLoginProfessor = findViewById(R.id.btnLoginProfessor);
        btnLoginAluno = findViewById(R.id.btnLoginAluno);

        db = AppDatabase.getDatabase(this);

        btnLoginProfessor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String senha = etSenha.getText().toString();
                new LoginProfessorTask().execute(email, senha);
            }
        });

        btnLoginAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String senha = etSenha.getText().toString();
                new LoginAlunoTask().execute(email, senha);
            }
        });
    }

    private class LoginProfessorTask extends AsyncTask<String, Void, Professor> {
        @Override
        protected Professor doInBackground(String... params) {
            String email = params[0];
            String senha = params[1];
            return db.professorDao().login(email, senha);
        }

        @Override
        protected void onPostExecute(Professor professor) {
            if (professor != null) {
                startActivity(new Intent(LoginActivity.this, ProfessorActivity.class));
            } else {
                Toast.makeText(LoginActivity.this, "Login falhou", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class LoginAlunoTask extends AsyncTask<String, Void, Aluno> {
        @Override
        protected Aluno doInBackground(String... params) {
            String email = params[0];
            String senha = params[1];
            return db.alunoDao().login(email, senha);
        }

        @Override
        protected void onPostExecute(Aluno aluno) {
            if (aluno != null) {
                startActivity(new Intent(LoginActivity.this, AlunoActivity.class));
            } else {
                Toast.makeText(LoginActivity.this, "Login falhou", Toast.LENGTH_SHORT).show();
            }
        }
    }
}