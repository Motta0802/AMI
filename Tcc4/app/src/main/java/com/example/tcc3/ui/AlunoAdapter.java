package com.example.tcc3.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tcc3.R;
import com.example.tcc3.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoAdapter extends RecyclerView.Adapter<AlunoAdapter.AlunoViewHolder> {

    private List<Aluno> alunos;
    private final OnItemClickListener listener;

    public AlunoAdapter(List<Aluno> alunos, OnItemClickListener listener) {
        this.alunos = alunos != null ? alunos : new ArrayList<>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public AlunoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_aluno, parent, false);
        return new AlunoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlunoViewHolder holder, int position) {
        Aluno aluno = alunos.get(position);
        holder.tvNomeAluno.setText(aluno.getNome());
        holder.tvEmailAluno.setText(aluno.getEmail());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(aluno);
            }
        });
    }

    @Override
    public int getItemCount() {
        return alunos.size();
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos != null ? alunos : new ArrayList<>();
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        if (position >= 0 && position < alunos.size()) {
            alunos.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void updateItem(int position, Aluno aluno) {
        if (position >= 0 && position < alunos.size() && aluno != null) {
            alunos.set(position, aluno);
            notifyItemChanged(position);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Aluno aluno);
    }

    static class AlunoViewHolder extends RecyclerView.ViewHolder {
        final TextView tvNomeAluno;
        final TextView tvEmailAluno;

        public AlunoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNomeAluno = itemView.findViewById(R.id.tvNomeAluno);
            tvEmailAluno = itemView.findViewById(R.id.tvEmailAluno);
        }
    }
}