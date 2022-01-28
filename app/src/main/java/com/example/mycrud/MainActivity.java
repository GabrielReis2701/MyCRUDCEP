package com.example.mycrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText nome,cpf,telefone,cep;
    private TextView tv_result;
    private Button bt_cep;
    private AlunoDAO dao;
    private  Aluno aluno = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        nome = findViewById(R.id.et_nome);
        cpf = findViewById(R.id.et_cpf);
        telefone = findViewById(R.id.et_telefone);
        cep = findViewById(R.id.et_cep);
        tv_result = findViewById(R.id.tvresultado);
        bt_cep = findViewById(R.id.bt_cep);
        dao = new AlunoDAO(this);

        Intent it = getIntent();
        if(it.hasExtra("aluno")){
            aluno = (Aluno) it.getSerializableExtra("aluno");
            nome.setText(aluno.getNome().toString());
            cpf.setText(aluno.getCpf().toString());
            telefone.setText(aluno.getTelefone().toString());
            cep.setText(aluno.getCep().toString());
        }
        telefone.addTextChangedListener(MaskEditUtil.mask(telefone,MaskEditUtil.FORMAT_FONE));
        cpf.addTextChangedListener(MaskEditUtil.mask(cpf,MaskEditUtil.FORMAT_CPF));
        cep.addTextChangedListener(MaskEditUtil.mask(cep,MaskEditUtil.FORMAT_CEP));

        bt_cep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<CEP> call = new RetrofitClient().getCEPService().consultCEP(cep.getText().toString());
                call.enqueue(new Callback<CEP>() {
                    @Override
                    public void onResponse(Call<CEP> call, Response<CEP> response) {
                        CEP cep1 = response.body();
                        if(!cep1.toString().equals("")){
                            tv_result.setText(cep1.toString());
                        }else{
                            System.out.println("Erro");
                        }
                    }

                    @Override
                    public void onFailure(Call<CEP> call, Throwable t) {
                        Log.e("CEPService","Erro ao buscar o CEP: "+t.getMessage());

                    }
                });
            }
        });
    }

    public void cadastro(View v){
        if(aluno== null){
            Aluno a = new Aluno();
            a.setNome(nome.getText().toString());
            a.setCpf(cpf.getText().toString());
            a.setTelefone(telefone.getText().toString());
            a.setCep(cep.getText().toString());

            long id = dao.inserir(a);
            Toast.makeText(this,"cadastro inserido com Id: "+id, Toast.LENGTH_LONG).show();
        }else{
            aluno.setNome(nome.getText().toString());
            aluno.setCpf(cpf.getText().toString());
            aluno.setTelefone(telefone.getText().toString());
            aluno.setCep(cep.getText().toString());
            dao.atualizar(aluno);
            Toast.makeText(this,"cadastro Atualizado com Id: ", Toast.LENGTH_LONG).show();
        }
    }
}