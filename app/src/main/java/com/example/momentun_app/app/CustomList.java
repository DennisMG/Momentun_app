package com.example.momentun_app.app;

/**
 * Created by dell on 27/08/2014.
 */
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class CustomList extends ArrayAdapter<String>{
    private final Activity context;
    private final String[] web;
    private final Integer[] imageId;
    public CustomList(Activity context,
                      String[] web, Integer[] imageId) {
        super(context, R.layout.gallery_layout, web);
        this.context = context;
        this.web = web;
        this.imageId = imageId;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.feeditem, null, true);
        //TextView txtTitle = (TextView) rowView.findViewById(R.id.userName);
        //ImageView imageView = (ImageView) rowView.findViewById(R.id.userPhoto);
        //txtTitle.setText(web[position]);
        //imageView.setImageResource(imageId[position]);
        return rowView;
    }
}