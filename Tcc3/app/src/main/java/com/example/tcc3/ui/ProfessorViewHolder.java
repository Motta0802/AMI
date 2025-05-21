package com.example.tcc3.ui;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tcc3.R;
import com.example.tcc3.model.Professor;

public class ProfessorViewHolder extends RecyclerView.ViewHolder {
    TextView tvNomeProfessor, tvEmailProfessor;

    public ProfessorViewHolder(View itemView) {
        super(itemView);
        tvNomeProfessor = itemView.findViewById(R.id.tvNomeProfessor);
        tvEmailProfessor = itemView.findViewById(R.id.tvEmailProfessor);
    }

    public void bind(Professor professor) {
        tvNomeProfessor.setText(professor.getNome());
        tvEmailProfessor.setText(professor.getEmail());
    }
}
