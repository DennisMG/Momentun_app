package com.example.momentun_app.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;


public class GifPreview extends ActionBarActivity {
    GifMovieView gif_Preview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gifpreview);
        gif_Preview=(GifMovieView)findViewById(R.id.gifPreview);

        Intent intent = getIntent();

        int idResource = intent.getIntExtra(GifGallery.EXTRA_MESSAGE, 0);
        //gif_Preview.setScaleType(ImageView.ScaleType.CENTER_CROP);
        gif_Preview.setMovieResource(idResource);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.itempreview, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
