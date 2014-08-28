package com.example.momentun_app.app;

import android.app.Activity;
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
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
    };
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View gallery = inflater.inflate(R.layout.gallery_layout, container, false);

        CustomList adapter = new CustomList((Activity)gallery.getContext(), web, imageId);
        list=(ListView)gallery.findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getActivity(), "You Clicked at " + web[+position], Toast.LENGTH_SHORT).show();
            }
        });

        return gallery;
    }
}
