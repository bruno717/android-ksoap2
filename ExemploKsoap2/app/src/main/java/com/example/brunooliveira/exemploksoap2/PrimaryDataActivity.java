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

import com.example.brunooliveira.exemploksoap2.tasks.PrimaryDataTask;

public class PrimaryDataActivity extends AppCompatActivity {

    private TextView textViewResponse;
    private EditText editTextValue;
    private Button btnCallService;
    private String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary_data);

        findViews();
        btnCallService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soapService();
            }
        });
    }

    private void soapService() {
        data = editTextValue.getText().toString();
        new PrimaryDataTask(data, new PrimaryDataTask.OnReturnServicePrimary() {
            @Override
            public void onCompletion(String response) {
                textViewResponse.setText(response);
            }

            @Override
            public void onError() {
                Toast.makeText(getBaseContext(), "Ocorreu um erro.", Toast.LENGTH_SHORT).show();
            }
        }).execute();
    }

    private void findViews() {
        textViewResponse = (TextView) findViewById(R.id.primary_data_textview_response);
        editTextValue = (EditText) findViewById(R.id.primary_data_edittext_valor_primario);
        btnCallService = (Button) findViewById(R.id.primary_data_button_chamar_servico);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent intent = null;

        switch (id) {
            case R.id.array_strings:
                intent = new Intent(this, ArrayStringsActivity.class);
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
