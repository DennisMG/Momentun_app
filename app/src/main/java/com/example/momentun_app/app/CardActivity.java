package com.example.momentun_app.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by dell on 26/08/2014.
 */
public class CardActivity extends Fragment {

    View Card;
    private ImageView openCameraButton;
    private ImageView createNewPostButton;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        InitializeWidgets(inflater, container);
        return Card;


    }

    private void InitializeWidgets(LayoutInflater inflater, ViewGroup container) {

        Card = inflater.inflate(R.layout.fragment_card_front, container, false);
        openCameraButton=(ImageView) Card.findViewById(R.id.OpenCamera);
        createNewPostButton=(ImageView) Card.findViewById(R.id.CreateNewPost);

        openCameraButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Card.getContext(), CameraIndependantActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

        createNewPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Card.getContext(), CreatePost.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }
}
