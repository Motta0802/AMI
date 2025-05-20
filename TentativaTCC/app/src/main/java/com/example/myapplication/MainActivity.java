package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.activity.AlunoActivity;
import com.example.myapplication.activity.MonitorActivity;
import com.example.myapplication.activity.ProfessorActivity;
import com.example.myapplication.bd.AlunoDbHelper;
import com.example.myapplication.bd.MonitorDbHelper;
import com.example.myapplication.bd.ProfessorDbHelper;

public class MainActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnLogin;
    private TextView tvErrorMessage;

    private AlunoDbHelper alunoDbHelper;
    private ProfessorDbHelper professorDbHelper;
    private MonitorDbHelper monitorDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializa os helpers do banco de dados
        alunoDbHelper = new AlunoDbHelper(this);
        professorDbHelper = new ProfessorDbHelper(this);
        monitorDbHelper = new MonitorDbHelper(this);

        // Vincula os componentes do layout
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvErrorMessage = findViewById(R.id.tvErrorMessage);

        // Configura o clique do botão de login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                // Verifica se os campos estão preenchidos
                if (username.isEmpty() || password.isEmpty()) {
                    tvErrorMessage.setText("Preencha todos os campos.");
                    tvErrorMessage.setVisibility(View.VISIBLE);
                    return;
                }

                // Verifica o login
                if (verificarLoginAluno(username, password)) {
                    // Redireciona para a tela do aluno
                    Intent intent = new Intent(MainActivity.this, AlunoActivity.class);
                    startActivity(intent);
                } else if (verificarLoginProfessor(username, password)) {
                    // Redireciona para a tela do professor
                    Intent intent = new Intent(MainActivity.this, ProfessorActivity.class);
                    startActivity(intent);
                } else if (verificarLoginMonitor(username, password)) {
                    // Redireciona para a tela do monitor
                    Intent intent = new Intent(MainActivity.this, MonitorActivity.class);
                    startActivity(intent);
                } else {
                    // Exibe mensagem de erro
                    tvErrorMessage.setText("Usuário ou senha incorretos.");
                    tvErrorMessage.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    // Método para verificar o login do aluno
    private boolean verificarLoginAluno(String username, String password) {
        Cursor cursor = alunoDbHelper.buscarTodosAlunos();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String matricula = cursor.getString(cursor.getColumnIndexOrThrow(AlunoDbHelper.COLUMN_MATRICULA));
                String nome = cursor.getString(cursor.getColumnIndexOrThrow(AlunoDbHelper.COLUMN_NOME));

                // Verifica se o nome ou matrícula correspondem ao username
                if (username.equals(matricula) || username.equals(nome)) {
                    // Aqui você pode adicionar uma verificação de senha (se necessário)
                    return true;
                }
            } while (cursor.moveToNext());
            cursor.close();
        }
        return false;
    }

    // Método para verificar o login do professor
    private boolean verificarLoginProfessor(String username, String password) {
        Cursor cursor = professorDbHelper.buscarTodosProfessores();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String matricula = cursor.getString(cursor.getColumnIndexOrThrow(ProfessorDbHelper.COLUMN_MATRICULA));
                String nome = cursor.getString(cursor.getColumnIndexOrThrow(ProfessorDbHelper.COLUMN_NOME));

                // Verifica se o nome ou matrícula correspondem ao username
                if (username.equals(matricula) || username.equals(nome)) {
                    // Aqui você pode adicionar uma verificação de senha (se necessário)
                    return true;
                }
            } while (cursor.moveToNext());
            cursor.close();
        }
        return false;
    }

    // Método para verificar o login do monitor
    private boolean verificarLoginMonitor(String username, String password) {
        Cursor cursor = monitorDbHelper.buscarTodosMonitores();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String matricula = cursor.getString(cursor.getColumnIndexOrThrow(MonitorDbHelper.COLUMN_MATRICULA));
                String nome = cursor.getString(cursor.getColumnIndexOrThrow(MonitorDbHelper.COLUMN_NOME));

                // Verifica se o nome ou matrícula correspondem ao username
                if (username.equals(matricula) || username.equals(nome)) {
                    // Aqui você pode adicionar uma verificação de senha (se necessário)
                    return true;
                }
            } while (cursor.moveToNext());
            cursor.close();
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        // Fecha os helpers do banco de dados ao destruir a Activity
        alunoDbHelper.close();
        professorDbHelper.close();
        monitorDbHelper.close();
        super.onDestroy();
    }
}