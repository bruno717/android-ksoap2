package com.example.brunooliveira.exemploksoap2.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.example.brunooliveira.exemploksoap2.config.AccessConfig;
import com.example.brunooliveira.exemploksoap2.models.Aluno;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by bruno.oliveira on 02/10/2015.
 */
public class ArrayAlunosTask extends AsyncTask<Void, Void, Boolean> {

    private List<Aluno> lista;
    private List<Aluno> mResponse = new ArrayList<Aluno>();
    private OnReturnServiceArrayAlunos mListener;

    public ArrayAlunosTask(List<Aluno> lista, OnReturnServiceArrayAlunos mListener) {
        this.lista = lista;
        this.mListener = mListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        final String METHOD_NAME = "getListAlunos";
        final String SOAP_ACTION = AccessConfig.NAMESPACE + METHOD_NAME;
        final String NAMESPACE = AccessConfig.NAMESPACE;
        final String URL = AccessConfig.URL;

        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            /*for (String item : lista) {
                request.addProperty("lista", item);
            }*/

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);

            HttpTransportSE httpTransport = new HttpTransportSE(URL);
            httpTransport.call(SOAP_ACTION, envelope);

            Vector response = (Vector) envelope.getResponse();
            //mResponse = new String[response.size()];

            for (int i = 0; i < response.size(); i++) {
                Aluno aluno = new Aluno();
                aluno.setId(response);
                mResponse.add();
            }


            return true;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("ERRO", e.toString());
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean sucess) {
        if (mListener != null) {
            if (sucess) mListener.onCompletion(mResponse);
            else mListener.onError();
        }
    }

    public interface OnReturnServiceArrayAlunos {
        public void onCompletion(List<Aluno> response);

        public void onError();
    }

}
