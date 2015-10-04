package com.example.brunooliveira.exemploksoap2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.brunooliveira.exemploksoap2.tasks.ArrayStringsTask;

/**
 * Created by bruno.oliveira on 02/10/2015.
 */
public class ArrayStringsActivity extends AppCompatActivity {

    private Button btnCallService;
    private ListView mList;
    private ArrayAdapter mAdapter;
    private String[] lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_strings);

        lista = new String[]{"dado 1", "dado 2", "dado 3"};

        findView();
        btnCallService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soapService();
            }
        });
    }

    private void soapService() {
        new ArrayStringsTask(lista, new ArrayStringsTask.OnReturnServiceArrayStrings() {

            @Override
            public void onCompletion(String[] response) {
                setupList(response);
            }

            @Override
            public void onError() {
                Toast.makeText(getBaseContext(), "Ocorreu um erro.", Toast.LENGTH_SHORT).show();
            }
        }).execute();

    }

    private void setupList(String[] list) {
        mAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        mList.setAdapter(mAdapter);
    }

    private void findView() {
        btnCallService = (Button) findViewById(R.id.primary_data_button_chamar_servico);
        mList = (ListView) findViewById(R.id.array_string_list);
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
            case R.id.aluno:
                intent = new Intent(this, AlunoActivity.class);
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
