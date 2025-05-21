package com.example.tcc3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tcc3.database.AppDatabase;
import com.example.tcc3.databinding.ActivityDisciplinaProfessorBinding;
import com.example.tcc3.model.Disciplina;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DisciplinaProfessorActivity extends AppCompatActivity {

    private ActivityDisciplinaProfessorBinding binding;
    private AppDatabase db;
    private ExecutorService executorService;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDisciplinaProfessorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = AppDatabase.getDatabase(this);
        executorService = Executors.newSingleThreadExecutor();

        setupListeners();
        loadDisciplinas();
    }

    private void setupListeners() {
        binding.btnAdicionarDisciplina.setOnClickListener(v -> {
            String nome = binding.etNomeDisciplina.getText().toString().trim();
            String descricao = binding.etDescricaoDisciplina.getText().toString().trim();
            String cargaHorariaStr = binding.etCargaHorariaDisciplina.getText().toString().trim();

            if (nome.isEmpty() || descricao.isEmpty() || cargaHorariaStr.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }

            int cargaHoraria;
            try {
                cargaHoraria = Integer.parseInt(cargaHorariaStr);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Carga horária inválida", Toast.LENGTH_SHORT).show();
                return;
            }

            Disciplina novaDisciplina = new Disciplina(nome, descricao, cargaHoraria);

            executorService.execute(() -> {
                db.disciplinaDao().insert(novaDisciplina);
                runOnUiThread(() -> {
                    Toast.makeText(this, "Disciplina adicionada!", Toast.LENGTH_SHORT).show();
                    clearFields();
                    loadDisciplinas();
                });
            });
        });
        binding.btnVoltar.setOnClickListener(v -> {
            Intent intent = new Intent(DisciplinaProfessorActivity.this, ProfessorActivity.class);
            startActivity(intent);
        });
        binding.btnVisualizarDisciplinas.setOnClickListener(v -> {
            Intent intent = new Intent(DisciplinaProfessorActivity.this, DisciplinaAlunoActivity.class);
            startActivity(intent);
        });

    }

    private void loadDisciplinas() {
        executorService.execute(() -> {
            List<Disciplina> disciplinas = db.disciplinaDao().getAll();
            runOnUiThread(() -> {
                adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
                for (Disciplina d : disciplinas) {
                    adapter.add(d.getNome() + " - " + d.getDescricao() + " (" + d.getCargaHoraria() + "h)");
                }
                binding.listViewDisciplinasProfessor.setAdapter(adapter);
            });
        });
    }

    private void clearFields() {
        binding.etNomeDisciplina.setText("");
        binding.etDescricaoDisciplina.setText("");
        binding.etCargaHorariaDisciplina.setText("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }

}
