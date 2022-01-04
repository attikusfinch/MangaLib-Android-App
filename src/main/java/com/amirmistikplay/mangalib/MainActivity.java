package com.amirmistikplay.mangalib;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton mFloatingButton;
    FloatingActionButton HomeButton, NewsButton, HelpButton, RandomButton;

    boolean isAllFabsVisible;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        mFloatingButton = findViewById(R.id.mainbutton);

        // find mini buttons
        HomeButton = findViewById(R.id.home);
        NewsButton = findViewById(R.id.news);
        HelpButton = findViewById(R.id.next);
        RandomButton = findViewById(R.id.random);

        HomeButton.setVisibility(View.GONE);
        NewsButton.setVisibility(View.GONE);
        HelpButton.setVisibility(View.GONE);
        RandomButton.setVisibility(View.GONE);

        WebView webView = (WebView) findViewById (R.id.webView);
        webView.setWebViewClient(new WebViewClientOptions());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setUserAgentString(webSettings.getUserAgentString());
        webView.setWebContentsDebuggingEnabled(true);
        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);

        webView.loadUrl("https://mangalib.me/");

        // Cookie manager for the webview
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);

        isAllFabsVisible = false;

        mFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isAllFabsVisible){
                    HomeButton.show();
                    NewsButton.show();
                    HelpButton.show();
                    RandomButton.show();

                    isAllFabsVisible = true;
                } else {
                    HomeButton.hide();
                    NewsButton.hide();
                    HelpButton.hide();
                    RandomButton.hide();

                    isAllFabsVisible = false;
                }
            }
        });
        HomeButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        webView.loadUrl("https://mangalib.me/");
                    }
                });
        NewsButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        webView.loadUrl("https://mangalib.me/news");
                    }
                });
        HelpButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        webView.loadUrl("https://mangalib.me/faq");
                    }
                });
        RandomButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int[] randomList = {1,5,6,4,8,9};
                        int randomNumber = new Random().nextInt(randomList.length);
                        webView.loadUrl("https://mangalib.me/manga-list?types[]=" + randomList[randomNumber]);
                    }
                });
        //была убрана из-за не надобности
//        NextButton.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        String PageNumber = "";
//                        String MangaLink;
//                        MangaLink = webView.getOriginalUrl();
//                        try {
//                            PageNumber = MangaLink.split("=")[1];
//                        } catch (ArrayIndexOutOfBoundsException e){
//                            Toast.makeText(MainActivity.this, "Не смог перелестнуть", Toast.LENGTH_LONG).show();
//                            return;
//                        }
//                        try {
//                            String Link = MangaLink.split(String.valueOf(PageNumber))[0] + (Integer.parseInt(PageNumber) + 1);
//                            webView.loadUrl(Link);
//                        } catch (Exception e){
//                            Toast.makeText(MainActivity.this, "Не смог перелестнуть", Toast.LENGTH_LONG).show();
//                        }
//                    }
//                });
    }

    @Override
    public void onBackPressed(){
        WebView webView = (WebView) findViewById (R.id.webView);
        if(webView.canGoBack()){
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}