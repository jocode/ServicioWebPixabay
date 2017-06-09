package com.example.serviciowebpixabay.Adapter;

import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.serviciowebpixabay.Images;
import com.example.serviciowebpixabay.R;
import com.github.snowdream.android.widget.SmartImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by Camilo on 9/06/2017.
 */

public class AdapterListView extends BaseAdapter {

        Context context;
        LayoutInflater layoutInflater;
        SmartImageView smartImageView;
        TextView tvId, tvTags;
        ArrayList<Images> list_images;

        public AdapterListView(Context applicationContext, ArrayList<Images> images) {
            this.context = applicationContext;
            this.list_images = images;
            layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return this.list_images.size();
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

            tvId.setText(list_images.get(position).getId());
            tvTags.setText(list_images.get(position).getTags());
            smartImageView.setImageUrl(list_images.get(position).getPreviewURL(), dimension);

            return viewGroup;
        }

}
