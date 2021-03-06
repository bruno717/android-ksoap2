package com.example.brunooliveira.exemploksoap2.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.example.brunooliveira.exemploksoap2.config.AccessConfig;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by bruno.oliveira on 02/10/2015.
 */
public class PrimaryDataTask extends AsyncTask<Void, Void, Boolean> {

    private String data;
    private String mResponse;
    private OnReturnServicePrimary mListener;

    public PrimaryDataTask(String data, OnReturnServicePrimary mListener) {
        this.data = data;
        this.mListener = mListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        final String METHOD_NAME = "getString";
        final String SOAP_ACTION = AccessConfig.NAMESPACE + METHOD_NAME;
        final String NAMESPACE = AccessConfig.NAMESPACE;
        final String URL = AccessConfig.URL;

        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("texto", data);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            //envelope.dotNet = true;
            envelope.setOutputSoapObject(request);

            HttpTransportSE httpTransport = new HttpTransportSE(URL);
            httpTransport.call(SOAP_ACTION, envelope);

            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();

            mResponse = response.toString();

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

    public interface OnReturnServicePrimary {
        public void onCompletion(String response);

        public void onError();
    }

}
