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

import java.io.*;
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
    private ImageView image2;
    private ImageView image3;
    private Camera.PictureCallback mPicture;
    public static final int MEDIA_TYPE_IMAGE = 1;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View cam = inflater.inflate(R.layout.camera_layout, container, false);
        image1=(ImageView)cam.findViewById(R.id.imageCameraButton);
        image2=(ImageView)cam.findViewById(R.id.imageGalleryButton);
        image3=(ImageView)cam.findViewById(R.id.imageRecordButton);

        WindowManager wm = (WindowManager) cam.getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display=wm.getDefaultDisplay();

        mPicture = new Camera.PictureCallback() {

            @Override
            public void onPictureTaken(byte[] data, Camera camera) {

                File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
                if (pictureFile == null){
                    Log.d("", "Error creating media file, check storage permissions: " );
                    return;
                }

                try {
                    //String file = pictureFile.toString();



                    FileOutputStream fos = new FileOutputStream(pictureFile);
                    byte[] file = data;
                    fos.write(data);
                    fos.flush();

                    BitmapFactory.Options bounds = new BitmapFactory.Options();
                    bounds.inJustDecodeBounds = true;
                    BitmapFactory.decodeByteArray(file,0,file.length, bounds);

                    BitmapFactory.Options opts = new BitmapFactory.Options();
                    Bitmap bm = BitmapFactory.decodeByteArray(file,0,file.length, opts);
                    ExifInterface exif = new ExifInterface(pictureFile.toString());
                    String orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
                    int orientation = orientString != null ? Integer.parseInt(orientString) : ExifInterface.ORIENTATION_NORMAL;
                    int rotationAngle = 0;
                    if (orientation == ExifInterface.ORIENTATION_ROTATE_90) rotationAngle = 90;
                    if (orientation == ExifInterface.ORIENTATION_ROTATE_180) rotationAngle = 180;
                    if (orientation == ExifInterface.ORIENTATION_ROTATE_270) rotationAngle = 270;

                    Matrix matrix = new Matrix();
                    matrix.setRotate(rotationAngle, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);
                    Bitmap rotatedBitmap = Bitmap.createBitmap(bm, 0, 0, bounds.outWidth, bounds.outHeight, matrix, true);

                    rotatedBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                    fos.flush();
                    fos.close();
                    mCamera.startPreview();
                } catch (FileNotFoundException e) {
                    Log.d("", "File not found: " + e.getMessage());
                } catch (IOException e) {
                    Log.d("", "Error accessing file: " + e.getMessage());
                }
            }
        };


        image1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // get an image from the camera
                        mCamera.takePicture(null, null, mPicture);




                    }
                }
        );

        setCorrectButtonWidth(getButtonWidth(display));

        mCamera = getCameraInstance();

        mPreview = new CameraPreview(this.getActivity().getBaseContext(), mCamera);
        preview = (FrameLayout) cam.findViewById(R.id.camera_preview);
        preview.addView(mPreview);
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

    public void setCorrectButtonWidth(int ButtonWidth) {
        image1.getLayoutParams().width=ButtonWidth;
        image2.getLayoutParams().width=ButtonWidth;
        image3.getLayoutParams().width=ButtonWidth;
        image1.requestLayout();
        image2.requestLayout();
        image3.requestLayout();
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
