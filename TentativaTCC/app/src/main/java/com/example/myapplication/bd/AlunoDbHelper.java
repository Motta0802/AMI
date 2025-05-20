package com.example.myapplication.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AlunoDbHelper extends SQLiteOpenHelper {

    // Nome do banco de dados
    private static final String DATABASE_NAME = "escola.db";

    // Versão do banco de dados (importante para atualizações futuras)
    private static final int DATABASE_VERSION = 1;

    // Nome da tabela
    public static final String TABLE_NAME = "aluno";

    // Colunas da tabela
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NOME = "nome";
    public static final String COLUMN_MATRICULA = "matricula";
    public static final String COLUMN_IDADE = "idade";
    public static final String COLUMN_CURSO = "curso";
    public static final String COLUMN_EMAIL = "email";



    // Comando SQL para criar a tabela
    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NOME + " TEXT NOT NULL, " +
                    COLUMN_MATRICULA + " TEXT NOT NULL, " +
                    COLUMN_IDADE + " INTEGER NOT NULL, " +
                    COLUMN_CURSO + " TEXT NOT NULL," +
                    COLUMN_EMAIL + " TEXT NOT NULL)";

    // Comando SQL para excluir a tabela
    private static final String SQL_DELETE_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    // Construtor
    public AlunoDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Executa o comando SQL para criar a tabela
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Atualiza o banco de dados (exclui a tabela antiga e cria uma nova)
        db.execSQL(SQL_DELETE_TABLE);
        onCreate(db);
    }

    // Método para inserir um aluno no banco de dados
    public long inserirAluno(String nome, String matricula, int idade, String curso, String email) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NOME, nome);
        values.put(COLUMN_MATRICULA, matricula);
        values.put(COLUMN_IDADE, idade);
        values.put(COLUMN_CURSO, curso);
        values.put(COLUMN_EMAIL, email);

        // Insere os dados e retorna o ID do registro inserido
        return db.insert(TABLE_NAME, null, values);
    }

    // Método para buscar todos os alunos
    public Cursor buscarTodosAlunos() {
        SQLiteDatabase db = this.getReadableDatabase();

        // Retorna um Cursor com todos os registros da tabela
        return db.query(
                TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    // Método para atualizar um aluno
    public int atualizarAluno(long id, String nome, String matricula, int idade, String curso, String email) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NOME, nome);
        values.put(COLUMN_MATRICULA, matricula);
        values.put(COLUMN_IDADE, idade);
        values.put(COLUMN_CURSO, curso);
        values.put(COLUMN_EMAIL, email);

        // Atualiza o registro com base no ID
        return db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)}
        );
    }

    // Método para excluir um aluno
    public int excluirAluno(long id) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Exclui o registro com base no ID
        return db.delete(
                TABLE_NAME,
                COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)}
        );
    }
}