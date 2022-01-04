package com.amirmistikplay.mangalib;

import android.webkit.WebView;

public class WebViewClientOptions extends android.webkit.WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, String url) {
        return false;
    }
}
