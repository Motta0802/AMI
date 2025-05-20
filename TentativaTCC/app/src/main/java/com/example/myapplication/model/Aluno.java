package com.example.myapplication.model;

public class Aluno {

        private String nome;
        private String matricula;
        private int idade;
        private String curso;
        private String email;

        public Aluno(String nome, String matricula, int idade, String curso, String email) {
            this.nome = nome;
            this.matricula = matricula;
            this.idade = idade;
            this.curso = curso;
            this.email = this.email;
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

        public int getIdade() {
            return idade;
        }

        public void setIdade(int idade) {
            if (idade > 0) { // Validação simples para idade
                this.idade = idade;
            } else {
                System.out.println("Idade inválida.");
            }
        }

        public String getCurso() {
            return curso;
        }

        public void setCurso(String curso) {
            this.curso = curso;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
        public String exibirInformacoes() {
            return "Nome: " + nome + "\n" +
                    "Matrícula: " + matricula + "\n" +
                    "Idade: " + idade + "\n" +
                    "Curso: " + curso;
        }
    }

