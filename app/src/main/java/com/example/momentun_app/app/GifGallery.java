package com.example.momentun_app.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by dell on 22/08/2014.
 */
public class GifGallery extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View gallery = inflater.inflate(R.layout.gallery_layout, container, false);
        ((TextView)gallery.findViewById(R.id.textView)).setText("GALLERY");
        return gallery;
    }
}
