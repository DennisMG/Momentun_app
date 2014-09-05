package com.example.momentun_app.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by dell on 22/08/2014.
 */
public class GifGallery extends Fragment {
    ListView list;
    private View gallery;
    public final static String EXTRA_MESSAGE = "com.example.momentun_app.MESSAGE";
    String[] web = {
            "Google Plus",
            "Twitter",
            "Windows",
            "Bing",
            "Itunes",
            "Wordpress",
            "Drupal"
    } ;
    Integer[] imageId = {
            R.drawable.tumblrdbz,
            R.drawable.tumblr,
            R.drawable.space,
            R.drawable.beauties,
            R.drawable.tumblr,
            R.drawable.tumblr2,
            R.drawable.beauties,
    };
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        gallery = inflater.inflate(R.layout.gallery_layout, container, false);

        CustomList adapter = new CustomList((Activity)gallery.getContext(), web, imageId);
        list=(ListView)gallery.findViewById(R.id.list);
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent myIntent = new Intent(parent.getContext(),GifPreview.class);

                int resource= imageId[position];
                myIntent.putExtra(EXTRA_MESSAGE,resource);
                startActivityForResult(myIntent, 0);
                ((Activity) gallery.getContext()).finish();
                Toast.makeText(getActivity(), "You Clicked at " + web[+position], Toast.LENGTH_SHORT).show();
            }
        });

        return gallery;
    }
}
