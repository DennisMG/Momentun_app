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
public class Camera extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View cam = inflater.inflate(R.layout.camera_layout, container, false);
        ((TextView)cam.findViewById(R.id.textView)).setText("CAMERA");
        return cam;
    }
}
