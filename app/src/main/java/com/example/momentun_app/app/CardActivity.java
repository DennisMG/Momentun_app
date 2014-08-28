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
    CardFlipActivity cardFlipActivity;
    ImageView openCameraActivity_Button;
    View Card;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        Card = inflater.inflate(R.layout.fragment_card_front, container, false);
        cardFlipActivity = new CardFlipActivity();

        return Card;


    }

    public void setOpenCamera(){
              Intent myIntent = new Intent(Card.getContext(), CameraActivity.class);
              startActivityForResult(myIntent, 0);


    }


}
