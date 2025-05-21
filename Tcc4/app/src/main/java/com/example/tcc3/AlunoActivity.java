package com.example.tcc3;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tcc3.databinding.ActivityAlunoBinding;
import com.example.tcc3.editar.EditarAlunoActivity;

public class AlunoActivity extends AppCompatActivity {

    private ActivityAlunoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAlunoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Botão de logout
        binding.btnLogoutAluno.setOnClickListener(v -> {
            Intent intent = new Intent(AlunoActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        // Botão de editar
        binding.btnEditarAluno.setOnClickListener(v -> {
            Intent intent = new Intent(AlunoActivity.this, EditarAlunoActivity.class);
            startActivity(intent);
        });
    }
}
