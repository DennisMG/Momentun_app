package com.example.momentun_app.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;


public class CreatePost extends ActionBarActivity {
    private Button fromGalleryButton;
    private Button doneButton;
    private ImageButton cameraButton;
    private static final int SELECT_PICTURE = 1;
    private ImageView  previewUploadImage;
    private Uri ImageUriPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        InitializeWidgets();

    }

    private void InitializeWidgets() {
        fromGalleryButton=(Button)findViewById(R.id.btn_Upload);
        previewUploadImage=(ImageView)findViewById(R.id.previewUploadPhoto);
        doneButton=(Button)findViewById(R.id.done_button);
        cameraButton=(ImageButton)findViewById(R.id.photoButton);

        String path = getPath();
        if(path!=null) {
            ImageUriPath = Uri.parse(getPath());
            previewUploadImage.setImageURI(ImageUriPath);
        }

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getBaseContext(), CameraIndependantActivity.class);
                startActivityForResult(myIntent, 0);
                finish();
            }
        });

        doneButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                sendPostToParse();
                sendToNewsFeed();
            }
        });

        fromGalleryButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(i,"Select Picture"), SELECT_PICTURE);
            }
        });
    }

    private String getPath() {
        Intent intent = getIntent();
        return intent.getStringExtra(CameraIndependantActivity.PATH);

    }

    private void sendToNewsFeed() {

    }

    private void sendPostToParse() {

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                previewUploadImage.setImageURI(selectedImageUri);
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.create_post, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
