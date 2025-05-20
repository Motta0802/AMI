package com.example.myapplication.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class AlunoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno);

        // Vincula o TextView do layout ao código
        TextView tvWelcome = findViewById(R.id.tvWelcome);

        // Verifica se o TextView foi encontrado
        if (tvWelcome != null) {
            // Recupera o nome do aluno passado pela Intent (opcional)
            String nomeAluno = getIntent().getStringExtra("NOME_ALUNO");
            if (nomeAluno != null) {
                tvWelcome.setText("Bem-vindo, " + nomeAluno + "!");
            } else {
                tvWelcome.setText("Bem-vindo, Aluno!");
            }
        } else {
            Log.e("AlunoActivity", "TextView tvWelcome não encontrado.");
        }

        Log.d("AlunoActivity", "Activity criada com sucesso.");
    }
}