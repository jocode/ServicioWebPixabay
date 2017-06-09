package com.example.serviciowebpixabay;


import org.json.JSONException;
import org.json.JSONObject;

public class Images {

    private String id;
    private String previewURL;
    private  String tags;

    public Images(JSONObject objetoJSON) throws JSONException {
        id = objetoJSON.getString("id");
        previewURL = objetoJSON.getString("previewURL");
        tags = objetoJSON.getString("tags");
    }

    public String getId() {
        return id;
    }

    public void String (String id) {
        this.id = id;
    }

    public String getPreviewURL() {
        return previewURL;
    }

    public void setPreviewURL(String previewURL) {
        this.previewURL = previewURL;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
