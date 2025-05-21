package com.example.tcc3;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tcc3.database.AppDatabase;
import com.example.tcc3.databinding.ActivityDisciplinaAlunoBinding;
import com.example.tcc3.model.Disciplina;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DisciplinaAlunoActivity extends AppCompatActivity {

    private ActivityDisciplinaAlunoBinding binding;
    private AppDatabase db;
    private ExecutorService executorService;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDisciplinaAlunoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = AppDatabase.getDatabase(this);
        executorService = Executors.newSingleThreadExecutor();

        loadDisciplinas();
    }

    private void loadDisciplinas() {
        executorService.execute(() -> {
            List<Disciplina> disciplinas = db.disciplinaDao().getAll();
            runOnUiThread(() -> {
                adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
                for (Disciplina d : disciplinas) {
                    adapter.add(d.getNome() + " - " + d.getDescricao());
                }
                binding.listViewDisciplinasAluno.setAdapter(adapter);
            });
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }
}
