package com.example.brunooliveira.exemploksoap2.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.example.brunooliveira.exemploksoap2.config.AccessConfig;
import com.example.brunooliveira.exemploksoap2.models.Aluno;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by bruno.oliveira on 02/10/2015.
 */
public class AlunoTask extends AsyncTask<Void, Void, Boolean> {

    private Aluno aluno;
    private OnReturnServiceAluno mListener;

    public AlunoTask(Aluno aluno, OnReturnServiceAluno mListener) {
        this.aluno = aluno;
        this.mListener = mListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        final String METHOD_NAME = "getAluno";
        final String SOAP_ACTION = AccessConfig.NAMESPACE + METHOD_NAME;
        final String NAMESPACE = AccessConfig.NAMESPACE;
        final String URL = AccessConfig.URL;

        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            PropertyInfo pi = new PropertyInfo();
            pi.setName("aluno");
            pi.setValue(aluno);
            pi.setType(aluno.getClass());

            request.addProperty(pi);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            //envelope.dotNet = true;
            envelope.setOutputSoapObject(request);

            HttpTransportSE httpTransport = new HttpTransportSE(URL);
            httpTransport.call(SOAP_ACTION, envelope);

            SoapObject response = (SoapObject) envelope.getResponse();

            aluno.setId(Integer.parseInt(response.getProperty("id").toString()));
            aluno.setNome(response.getProperty("nome").toString());
            aluno.setCurso(response.getProperty("curso").toString());

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
            if (sucess) mListener.onCompletion(aluno);
            else mListener.onError();
        }
    }

    public interface OnReturnServiceAluno {
        public void onCompletion(Aluno responseAluno);

        public void onError();
    }

}
