package com.example.tcc3.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tcc3.databinding.ItemAlunoBinding;
import com.example.tcc3.model.Aluno;
import java.util.List;

public class AlunoAdapter extends RecyclerView.Adapter<AlunoAdapter.AlunoViewHolder> {

    public interface OnAlunoClickListener {
        void onAlunoClick(Aluno aluno);
    }

    private List<Aluno> alunos;
    private final OnAlunoClickListener listener;

    public AlunoAdapter(List<Aluno> alunos, OnAlunoClickListener listener) {
        this.alunos = alunos;
        this.listener = listener;
    }

    public void setAlunos(List<Aluno> novosAlunos) {
        this.alunos = novosAlunos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AlunoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemAlunoBinding binding = ItemAlunoBinding.inflate(inflater, parent, false);
        return new AlunoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AlunoViewHolder holder, int position) {
        Aluno aluno = alunos.get(position);
        holder.binding.tvNomeAluno.setText(aluno.nome);
        holder.binding.tvEmailAluno.setText(aluno.email);

        holder.binding.getRoot().setOnClickListener(v -> listener.onAlunoClick(aluno));
    }

    @Override
    public int getItemCount() {
        return alunos != null ? alunos.size() : 0;
    }

    public static class AlunoViewHolder extends RecyclerView.ViewHolder {
        final ItemAlunoBinding binding;

        public AlunoViewHolder(ItemAlunoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
