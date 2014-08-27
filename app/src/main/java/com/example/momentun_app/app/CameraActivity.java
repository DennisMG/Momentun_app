package com.example.momentun_app.app;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.hardware.Camera;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.*;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.hardware.Camera.open;
/**
 * Created by dell on 22/08/2014.
 */
public class CameraActivity extends Fragment {

    private static final int MEDIA_TYPE_VIDEO = 2;
    private static Camera mCamera;
    private CameraPreview mPreview;
    private FrameLayout preview;
    private ImageView image1;
    private Camera.PictureCallback mPicture;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public View cam;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        cam = inflater.inflate(R.layout.camera_layout, container, false);
        image1=(ImageView)cam.findViewById(R.id.imageCameraButton);
        WindowManager wm = (WindowManager) cam.getContext().getSystemService(Context.WINDOW_SERVICE);

        mPicture = new Camera.PictureCallback() {

            @Override
            public void onPictureTaken(byte[] data, Camera camera) {

                File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
                if (pictureFile == null){
                    Log.d("", "Error creating media file, check storage permissions: " );
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

                } catch (FileNotFoundException e) {
                    Log.d("", "File not found: " + e.getMessage());
                } catch (IOException e) {
                    Log.d("", "Error accessing file: " + e.getMessage());
                }

                try {
                    ExifInterface exif = new ExifInterface(pictureFile.getAbsolutePath());
                    exif.setAttribute(ExifInterface.TAG_ORIENTATION, String.valueOf(90));
                    exif.saveAttributes();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        };


        image1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        mCamera.takePicture(null, null, mPicture);

                    }
                }
        );



        mCamera = getCameraInstance();

        mPreview = new CameraPreview(this.getActivity().getBaseContext(), mCamera);
        preview = (FrameLayout) cam.findViewById(R.id.camera_preview);
        preview.addView(mPreview);
        mCamera.setDisplayOrientation(90);
        return cam;
    }

    private int getButtonWidth(Display d) {

        Point size = new Point();
        d.getSize(size);
        int width = size.x/3;
        return width;

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



    private static Uri getOutputMediaFileUri(int type){
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /** Create a File for saving an image or video */
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


}

