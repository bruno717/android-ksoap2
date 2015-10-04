package com.example.brunooliveira.exemploksoap2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brunooliveira.exemploksoap2.models.Aluno;
import com.example.brunooliveira.exemploksoap2.tasks.AlunoTask;

/**
 * Created by bruno.oliveira on 02/10/2015.
 */
public class AlunoActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextClass;
    private Button btnCallService;
    private TextView textViewName;
    private TextView textViewClass;
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno);

        aluno = new Aluno();
        findView();
        btnCallService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aluno.setNome(editTextName.getText().toString());
                aluno.setCurso(editTextClass.getText().toString());
                soapService();
            }
        });
    }

    private void soapService() {
        new AlunoTask(aluno, new AlunoTask.OnReturnServiceAluno() {
            @Override
            public void onCompletion(Aluno responseAluno) {
                textViewName.setText(responseAluno.getNome());
                textViewClass.setText(responseAluno.getCurso());
            }

            @Override
            public void onError() {
                Toast.makeText(getBaseContext(), "Ocorreu um erro.", Toast.LENGTH_SHORT).show();

            }
        }).execute();
    }

    private void findView() {
        editTextName = (EditText) findViewById(R.id.aluno_edittext_nome);
        editTextClass = (EditText) findViewById(R.id.aluno_edittext_curso);
        btnCallService = (Button) findViewById(R.id.aluno_button_chamar_servico);
        textViewName = (TextView) findViewById(R.id.aluno_text_nome);
        textViewClass = (TextView) findViewById(R.id.aluno_text_curso);
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
            case R.id.array_alunos:
                intent = new Intent(this, ArrayAlunosActivity.class);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
