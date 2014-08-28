package com.example.momentun_app.app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by dell on 26/08/2014.
 */
public class CardFrontFragment extends Fragment {



    View cardFront;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        cardFront = inflater.inflate(R.layout.gallery_layout, container, false);
        ImageView openCamera = (ImageView) getActivity().findViewById(R.id.pictureCamera);

        /*openCamera.setOnClickListener(
                new ImageView.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent myIntent = new Intent(cardFront.getContext(), CameraIndependantActivity.class);
                        startActivityForResult(myIntent, 0);

                    }
                }
        );*/


        return cardFront;
    }




}
