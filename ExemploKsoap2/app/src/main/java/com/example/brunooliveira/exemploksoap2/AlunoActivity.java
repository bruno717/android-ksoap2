package com.example.brunooliveira.exemploksoap2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by bruno.oliveira on 02/10/2015.
 */
public class AlunoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno);
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
