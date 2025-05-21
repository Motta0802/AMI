package com.example.tcc3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class AlunoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno);

        Button btnLogout = findViewById(R.id.btnLogoutAluno);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Voltar para a tela de login
                Intent intent;
                intent = new Intent(AlunoActivity.this, LoginActivity.class);
                startActivity(intent);
                finish(); // Finaliza a atividade atual
            }
        });
    }
}