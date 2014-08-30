package com.example.momentun_app.app.GifAnimationPackage;

import android.content.Context;
import android.webkit.WebView;

/**
 * Created by dell on 29/08/2014.
 */
public class GifWebView extends WebView {

    public GifWebView(Context context, String path) {
        super(context);

        loadUrl(path);
    }
}