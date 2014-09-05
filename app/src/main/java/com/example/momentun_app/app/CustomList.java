package com.example.momentun_app.app;

/**
 * Created by dell on 27/08/2014.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class CustomList extends ArrayAdapter<String>{
    private final Activity context;
    private final String[] web;
    private final Integer[] imageId;

    public CustomList(Activity context, String[] web, Integer[] imageId) {
        super(context, R.layout.gallery_layout, web);
        this.context = context;
        this.web = web;
        this.imageId = imageId;


    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.feeditem,null, true);
        ImageView img=(ImageView)rowView.findViewById(R.id.imageView2);
        img.setImageResource(imageId[position]);
        return rowView;
    }
}