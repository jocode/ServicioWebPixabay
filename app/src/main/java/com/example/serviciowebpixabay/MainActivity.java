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

                        Log.e("Hola: ", jsonArray.getString(0));

                        for (int i = 0; i < jsonArray.length(); i++){
                            id.add(jsonArray.getJSONObject(i).getString("id"));
                            tags.add(jsonArray.getJSONObject(i).getString("tags"));
                            imagen.add(jsonArray.getJSONObject(i).getString("previewURL"));
                        }

                        lvResponse.setAdapter(new AdapterListView(getApplicationContext()));

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

    private  class AdapterListView extends BaseAdapter {

        Context context;
        LayoutInflater layoutInflater;
        SmartImageView smartImageView;
        TextView tvId, tvTags;

        public AdapterListView(Context applicationContext) {
            this.context = applicationContext;
            layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return imagen.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewGroup viewGroup = (ViewGroup) layoutInflater.inflate(R.layout.items_image, null);

            smartImageView = (SmartImageView) viewGroup.findViewById(R.id.smImage);
            tvId = (TextView) viewGroup.findViewById(R.id.tvIdImage);
            tvTags = (TextView) viewGroup.findViewById(R.id.tvTags);

            Rect dimension = new Rect(smartImageView.getLeft(), smartImageView.getTop(), smartImageView.getRight(), smartImageView.getBottom());
            smartImageView.setImageUrl(imagen.get(position).toString(), dimension);

            tvId.setText(id.get(position).toString());
            tvTags.setText(tags.get(position).toString());

            return viewGroup;
        }
    }
}
