package com.example.myapplication.model;

public class Professor {

        private String nome;
        private String matricula;
        private String disciplina;
        private int cargaHoraria;


        public Professor(String nome, String matricula, String disciplina, int cargaHoraria) {
            this.nome = nome;
            this.matricula = matricula;
            this.disciplina = disciplina;
            this.cargaHoraria = cargaHoraria;
        }


        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getMatricula() {
            return matricula;
        }

        public void setMatricula(String matricula) {
            this.matricula = matricula;
        }

        public String getDisciplina() {
            return disciplina;
        }

        public void setDisciplina(String disciplina) {
            this.disciplina = disciplina;
        }

        public int getCargaHoraria() {
            return cargaHoraria;
        }

        public void setCargaHoraria(int cargaHoraria) {
            if (cargaHoraria > 0) { // Validação simples para carga horária
                this.cargaHoraria = cargaHoraria;
            } else {
                System.out.println("Carga horária inválida.");
            }
        }


        public String exibirInformacoes() {
            return "Nome: " + nome + "\n" +
                    "Matrícula: " + matricula + "\n" +
                    "Disciplina: " + disciplina + "\n" +
                    "Carga Horária: " + cargaHoraria + " horas";
        }
    }

