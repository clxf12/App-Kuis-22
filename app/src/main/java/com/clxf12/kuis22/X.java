package com.clxf12.kuis22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.media.MediaPlayer;
import android.widget.ImageView;
import android.widget.TextView;

public class X extends AppCompatActivity {

    private ImageView dpC, dpF;

    MediaPlayer mp;

    private pl.droidsonroids.gif.GifImageView dnce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_x);

        MediaPlayer mp = MediaPlayer.create(this, R.raw.musik);
        mp.setLooping(true);
        mp.start();


        dpC = findViewById(R.id.dpC);
        dpF = findViewById(R.id.dpF);
        dnce = findViewById(R.id.dnce);


        dnce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ext = new Intent(Intent.ACTION_MAIN);
                ext.addCategory(Intent.CATEGORY_HOME);
                ext.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                ext.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                mp.stop();
                finishAndRemoveTask();
                startActivity(ext);
            }

        });

        dpC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String theurl = "https://www.instagram.com/clxf12";
                Uri urlstr = Uri.parse(theurl);
                Intent urlintent = new Intent();
                urlintent.setData(urlstr);
                urlintent.setAction(Intent.ACTION_VIEW);
                startActivity(urlintent);
            }
        });

        dpF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String theurl = "https://www.instagram.com/flxto_";
                Uri urlstr = Uri.parse(theurl);
                Intent urlintent = new Intent();
                urlintent.setData(urlstr);
                urlintent.setAction(Intent.ACTION_VIEW);
                startActivity(urlintent);
            }
        });



    }
}