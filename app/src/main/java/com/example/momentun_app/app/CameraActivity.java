package com.example.momentun_app.app;


import android.hardware.Camera;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import static android.hardware.Camera.open;

/**
 * Created by dell on 22/08/2014.
 */
public class CameraActivity extends Fragment {

    private Camera mCamera;
    private CameraPreview mPreview;
    private FrameLayout preview;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View cam = inflater.inflate(R.layout.camera_layout, container, false);
        ((TextView)cam.findViewById(R.id.textView)).setText("CAMERA");

        mCamera = getCameraInstance();

        mPreview = new CameraPreview(this.getActivity().getBaseContext(), mCamera);
        preview = (FrameLayout) cam.findViewById(R.id.camera_preview);
        preview.addView(mPreview);
        return cam;
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
}
