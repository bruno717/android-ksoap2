package com.example.brunooliveira.exemploksoap2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.brunooliveira.exemploksoap2.models.Aluno;
import com.example.brunooliveira.exemploksoap2.tasks.ArrayAlunosTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bruno.oliveira on 02/10/2015.
 */
public class ArrayAlunosActivity extends AppCompatActivity {

    private Button btnCallService;
    private EditText editTextName;
    private EditText editTextClass;
    private ListView mList;
    private List<Aluno> listAlunos;
    private ArrayAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_alunos);

        listAlunos = new ArrayList<Aluno>();
        findViews();
        btnCallService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Aluno aluno = new Aluno();
                aluno.setNome(editTextName.getText().toString());
                aluno.setCurso(editTextClass.getText().toString());
                listAlunos.add(aluno);
                soapService();
            }
        });
    }

    private void soapService() {
        new ArrayAlunosTask(listAlunos, new ArrayAlunosTask.OnReturnServiceArrayAlunos() {
            @Override
            public void onCompletion(List<Aluno> response) {
                setupList(response);
            }

            @Override
            public void onError() {
                Toast.makeText(getBaseContext(), "Ocorreu um erro.", Toast.LENGTH_SHORT).show();
            }
        }).execute();
    }

    private void setupList(List<Aluno> list) {
        List<String> strList = new ArrayList<String>();
        for (Aluno aluno : list){
            strList.add(aluno.getNome());
        }
        mAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, strList);
        mList.setAdapter(mAdapter);
    }

    private void findViews() {
        btnCallService = (Button) findViewById(R.id.array_alunos_button_chamar_servico);
        editTextName = (EditText) findViewById(R.id.aluno_edittext_nome);
        editTextClass = (EditText) findViewById(R.id.aluno_edittext_curso);
        mList = (ListView) findViewById(R.id.array_alunos_list);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent = null;

        switch (id) {
            case R.id.dado_primario:
                intent = new Intent(this, PrimaryDataActivity.class);
                startActivity(intent);
                return true;
            case R.id.array_strings:
                intent = new Intent(this, ArrayStringsActivity.class);
                startActivity(intent);
                return true;
            case R.id.aluno:
                intent = new Intent(this, AlunoActivity.class);
                startActivity(intent);
                return true;

        }

        return super.onOptionsItemSelected(item);
    }
}
