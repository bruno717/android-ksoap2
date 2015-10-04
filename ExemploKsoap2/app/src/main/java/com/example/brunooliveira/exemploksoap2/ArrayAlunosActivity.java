package com.example.brunooliveira.exemploksoap2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.brunooliveira.exemploksoap2.models.Aluno;
import com.example.brunooliveira.exemploksoap2.tasks.ArrayAlunosTask;

import java.util.List;

/**
 * Created by bruno.oliveira on 02/10/2015.
 */
public class ArrayAlunosActivity extends AppCompatActivity {

    private Button btnCallService;
    private ListView mList;
    private List<Aluno> listAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_alunos);

        findViews();
        btnCallService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soapService();
            }
        });
    }

    private void soapService() {
        new ArrayAlunosTask(listAlunos, new ArrayAlunosTask.OnReturnServiceArrayAlunos() {
            @Override
            public void onCompletion(List<Aluno> response) {
                Toast.makeText(getBaseContext(), "foi", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError() {
                Toast.makeText(getBaseContext(), "Ocorreu um erro.", Toast.LENGTH_SHORT).show();
            }
        }).execute();
    }

    private void findViews() {
        btnCallService = (Button) findViewById(R.id.array_alunos_button_chamar_servico);
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
