package com.example.myapplication.model;

public class Monitor extends Aluno {

        // Propriedades específicas da classe Monitor
        private String disciplinaMonitorada;
        private double bolsaAuxilio;

        // Construtor
        public Monitor(String nome, String matricula, int idade, String curso, String email, String disciplinaMonitorada, double bolsaAuxilio) {
            super(nome, matricula, idade, curso, email); // Chama o construtor da classe Aluno
            this.disciplinaMonitorada = disciplinaMonitorada;
            this.bolsaAuxilio = bolsaAuxilio;
        }

        // Métodos getters e setters
        public String getDisciplinaMonitorada() {
            return disciplinaMonitorada;
        }

        public void setDisciplinaMonitorada(String disciplinaMonitorada) {
            this.disciplinaMonitorada = disciplinaMonitorada;
        }

        public double getBolsaAuxilio() {
            return bolsaAuxilio;
        }

        public void setBolsaAuxilio(double bolsaAuxilio) {
            if (bolsaAuxilio >= 0) { // Validação simples para bolsa auxílio
                this.bolsaAuxilio = bolsaAuxilio;
            } else {
                System.out.println("Valor da bolsa auxílio inválido.");
            }
        }

        // Método para exibir informações do monitor
        @Override
        public String exibirInformacoes() {
            return super.exibirInformacoes() + "\n" +
                    "Disciplina Monitorada: " + disciplinaMonitorada + "\n" +
                    "Bolsa Auxílio: R$ " + bolsaAuxilio;
        }
    }

