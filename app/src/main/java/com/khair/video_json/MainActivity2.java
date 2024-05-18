package com.khair.video_json;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    ImageView imageView;
    WebView webView;

    public static String string="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        imageView=findViewById(R.id.image_Back);
        webView=findViewById(R.id.WebView);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity2.super.onBackPressed();
            }
        });


        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.youtube.com/embed/"+string);

    }
}