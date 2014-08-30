package com.example.momentun_app.app;

/**
 * Created by dell on 27/08/2014.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.example.momentun_app.app.GifAnimationPackage.GifWebView;

import java.io.IOException;
import java.io.InputStream;

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
        //GifImageView iv;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.feeditem,null, true);
        //gifView = new GifImageView(context);
        //gifView.setBytes(bitmapData);
        //setContentView(gifView);


       //WebView webView=(WebView)rowView.findViewById(R.id.imageView2);

        InputStream stream = null;
        try {
            stream = getContext().getAssets().open("tumblrdbz.gif");
        } catch (IOException e) {
            e.printStackTrace();
        }
        GifWebView view2 = new GifWebView(this.getContext(), "file:///android_asset/tumblrdbz.gif");
        //webView.setBackground(view2.getBackground());
//        webView.setBackgroundResource(R.drawable.tumblr2);
        //webView.loadUrl("C:\\Users\\dell\\Documents\\Momentun_app\\app\\Assets");
        //webView.loadUrl("https://33.media.tumblr.com/830c63dc994cbdd036c174a45204aa7c/tumblr_nb208dOYtT1thwc9so1_500.gif");
        //TextView txtTitle = (TextView) rowView.findViewById(R.id.userName);
        //ImageView imageView = (ImageView) rowView.findViewById(R.id.userPhoto);
        //txtTitle.setText(web[position]);
        //imageView.setImageResource(imageId[position]);
        return rowView;
    }
}