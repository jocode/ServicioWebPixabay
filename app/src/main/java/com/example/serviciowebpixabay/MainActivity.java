package com.example.serviciowebpixabay;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.serviciowebpixabay.Adapter.AdapterListView;
import com.github.snowdream.android.widget.SmartImageView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    ListView lvResponse;

    //Lista donde almacenaremos los objetos
    ArrayList<Images> list_images = new ArrayList<>();
    ArrayList id = new ArrayList();
    ArrayList tags = new ArrayList();
    ArrayList imagen = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvResponse = (ListView) findViewById(R.id.lvResponse);
        
        downloadImage();

    }

    private void downloadImage() {
        id.clear();
        tags.clear();
        imagen.clear();

        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Cargando Datos...");
        progressDialog.show();

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://pixabay.com/api/?key=4792335-cd8af72a09ac90bceb8bb8c63&category=nature", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200){
                    progressDialog.dismiss();

                    try {

                        JSONObject jsonObject = new JSONObject((new String(responseBody)));
                        JSONArray jsonArray = jsonObject.optJSONArray("hits");

                        for (int i = 0; i < jsonArray.length(); i++){
                            list_images.add(new Images(jsonArray.getJSONObject(i)));
                        }

                        lvResponse.setAdapter(new AdapterListView(getApplicationContext(), list_images));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

}
