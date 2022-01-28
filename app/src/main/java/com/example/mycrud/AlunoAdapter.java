package com.example.mycrud;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AlunoAdapter extends BaseAdapter {

    private List<Aluno> alunos;
    private Activity activity;

    public AlunoAdapter(Activity activity,List<Aluno> alunos){
        this.activity = activity;
        this.alunos = alunos;
    }

    @Override
    public int getCount(){return alunos.size();}

    @Override
    public Object getItem(int i){return alunos.get(i);}

    @Override
    public long getItemId(int i){ return alunos.get(i).getId();}

    @Override
    public View getView(int i, View view, ViewGroup viewGroup){
        View v = activity.getLayoutInflater().inflate(R.layout.item,viewGroup, false);
        TextView nome = v.findViewById(R.id.tv_nomei);
        TextView cpf = v.findViewById(R.id.tv_cpfi);
        TextView telefone = v.findViewById(R.id.tv_telefonei);
        TextView cep = v.findViewById(R.id.tv_cepi);
        Aluno a = alunos.get(i);
        nome.setText(a.getNome());
        cpf.setText(a.getCpf());
        telefone.setText(a.getTelefone());
        cep.setText(a.getCep());

        return v;

    }


}
