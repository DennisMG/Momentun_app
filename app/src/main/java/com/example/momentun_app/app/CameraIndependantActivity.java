package com.example.momentun_app.app;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.hardware.Camera.open;


public class CameraIndependantActivity extends Activity {

    private static final int MEDIA_TYPE_VIDEO = 2;
    private static Camera mCamera;
    private CameraPreview mPreview;
    private FrameLayout preview;
    private ImageView image1;
    private Camera.PictureCallback mPicture;
    public static final int MEDIA_TYPE_IMAGE = 1;
    private ActionBar actionBar;
    private Camera.AutoFocusCallback myAutoFocusCallback;
    public View cam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_layout);
        actionBar= getActionBar();
        actionBar.hide();
        image1=(ImageView)findViewById(R.id.takePicture);
        preview = (FrameLayout) findViewById(R.id.camera_preview);

        preview.setOnClickListener(new FrameLayout.OnClickListener(){

            @Override
            public void onClick(View v) {
                mCamera.autoFocus(new Camera.AutoFocusCallback() {
                    @Override
                    public void onAutoFocus(boolean success, Camera camera) {
                        if(success){
                            Log.d("MyCameraApp", "Se enfoco bien!!!! :)");
                        }
                    }
                });
            }
        });

        mPicture = new Camera.PictureCallback() {

            @Override
            public void onPictureTaken(byte[] data, Camera camera) {


                File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
                if (pictureFile == null){
                    Log.d("", "Error creating media file, check storage permissions: ");
                    return;
                }
                try {
                    FileOutputStream fos = new FileOutputStream(pictureFile);
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 6;
                    options.inDither = false; // Disable Dithering mode
                    options.inPurgeable = true; // Tell to gc that whether it needs free

                    options.inInputShareable = true;

                    options.inTempStorage = new byte[32 * 1024];
                    options.inPreferredConfig = Bitmap.Config.RGB_565;
                    Bitmap bMap = BitmapFactory.decodeByteArray(data, 0, data.length, options);
                    Matrix matrix = new Matrix();
                    matrix.postRotate(90);
                    Bitmap bMapRotate = Bitmap.createBitmap(bMap, 0, 0, bMap.getWidth(),
                            bMap.getHeight(), matrix, true);
                    bMapRotate.compress(Bitmap.CompressFormat.JPEG, 90, fos);

                    fos.write(data);
                    fos.flush();
                    fos.close();

                    mCamera.startPreview();
                    image1.setEnabled(true);


                } catch (FileNotFoundException e) {
                    Log.d("", "File not found: " + e.getMessage());
                } catch (IOException e) {
                    Log.d("", "Error accessing file: " + e.getMessage());
                }




            }
        };


        image1.setOnClickListener(
                new ImageView.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        image1.setEnabled(false);
                        mCamera.takePicture(null, null, mPicture);
                        /*image1.setEnabled(false);
                        mCamera.autoFocus(myAutoFocusCallback);
                        mCamera.takePicture(null, null, mPicture);*/

                    }
                }
        );



        mCamera = getCameraInstance();

        mPreview = new CameraPreview(this.getBaseContext(), mCamera);

        preview.addView(mPreview);
        mCamera.setDisplayOrientation(90);
    }

    private Camera getCameraInstance() {
        Camera c = null;
        try {
            c = open(); // attempt to get a Camera instance
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }

    private static File getOutputMediaFile(int type){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "Momentum");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");

        } else if(type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_"+ timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.camera_independant, menu);
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
