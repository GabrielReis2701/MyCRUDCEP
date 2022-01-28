package com.example.mycrud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    private Conexao conexao;
    private SQLiteDatabase banco;

    public AlunoDAO(Context context){
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    public long inserir(Aluno aluno){
        ContentValues values = new ContentValues();
        values.put("nome",aluno.getNome());
        values.put("cpf",aluno.getCpf());
        values.put("telefone",aluno.getTelefone());
        values.put("cep",aluno.getCep());

        return banco.insert("aluno",null, values);
    }
    public void excluir(Aluno a){
        banco.delete("aluno","id = ?", new String[]{a.getId().toString()});
    }
    public void atualizar(Aluno a){
        ContentValues values = new ContentValues();
        values.put("nome",a.getNome());
        values.put("cpf",a.getCpf());
        values.put("telefone",a.getTelefone());
        values.put("cep",a.getCep());
        banco.update("aluno",values, "id = ?",new String[]{a.getId().toString()});
    }

    public List<Aluno> obterTodos(){
        List<Aluno> alunos = new ArrayList<>();
        Cursor cursor = banco.query("aluno",new String[]{"id","nome","cpf","telefone","cep"}, null,null,null,null,null);
        while(cursor.moveToNext()){
            Aluno a = new Aluno();
            a.setId(cursor.getInt(0));
            a.setNome(cursor.getString(1));
            a.setCpf(cursor.getString(2));
            a.setTelefone(cursor.getString(3));
            a.setCep(cursor.getString(4));
            alunos.add(a);
        }
        return alunos;
    }
}
