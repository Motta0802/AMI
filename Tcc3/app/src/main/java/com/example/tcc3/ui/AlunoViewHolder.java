package com.example.tcc3.ui;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tcc3.R;
import com.example.tcc3.model.Aluno;

public class AlunoViewHolder extends RecyclerView.ViewHolder {
    TextView tvNomeAluno, tvEmailAluno;

    public AlunoViewHolder(View itemView) {
        super(itemView);
        tvNomeAluno = itemView.findViewById(R.id.tvNomeAluno);
        tvEmailAluno = itemView.findViewById(R.id.tvEmailAluno);
    }

    public void bind(Aluno aluno) {
        tvNomeAluno.setText(aluno.getNome());
        tvEmailAluno.setText(aluno.getMatricula());
    }
}
