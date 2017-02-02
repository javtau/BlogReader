package com.jjv.blogreader_javier_lozano.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.jjv.blogreader_javier_lozano.R;

public class WebActivity extends AppCompatActivity {
    WebView wv_entrada ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        wv_entrada = (WebView) findViewById(R.id.webview_blog);
        Intent i = getIntent();
        wv_entrada.loadUrl(i.getStringExtra(MainActivity.URL_ENTRADA));
    }
}
