package com.example.tcc3.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tcc3.R;
import com.example.tcc3.model.Professor;
import java.util.List;
import java.util.ArrayList;

public class ProfessorAdapter extends RecyclerView.Adapter<ProfessorAdapter.ProfessorViewHolder> {

    private List<Professor> professores;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Professor professor);
    }

    public ProfessorAdapter(List<Professor> professores, OnItemClickListener listener) {
        this.professores = professores != null ? professores : new ArrayList<>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProfessorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_professor, parent, false);
        return new ProfessorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfessorViewHolder holder, int position) {
        Professor professor = professores.get(position);
        holder.tvNomeProfessor.setText(professor.getNome());
        holder.tvEmailProfessor.setText(professor.getEmail());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(professor);
            }
        });
    }

    @Override
    public int getItemCount() {
        return professores.size();
    }

    public void setProfessores(List<Professor> professores) {
        this.professores = professores != null ? professores : new ArrayList<>();
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        if (position >= 0 && position < professores.size()) {
            professores.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void updateItem(int position, Professor professor) {
        if (position >= 0 && position < professores.size() && professor != null) {
            professores.set(position, professor);
            notifyItemChanged(position);
        }
    }

    static class ProfessorViewHolder extends RecyclerView.ViewHolder {
        TextView tvNomeProfessor, tvEmailProfessor;

        public ProfessorViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNomeProfessor = itemView.findViewById(R.id.tvNomeProfessor);
            tvEmailProfessor = itemView.findViewById(R.id.tvEmailProfessor);
        }
    }
}