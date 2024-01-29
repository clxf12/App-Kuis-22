package com.clxf12.kuis22;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import pl.droidsonroids.gif.GifImageView;

public class Score extends AppCompatActivity {

    private TextView hasil_score, score_benar, score_salah;
    private ImageView soal_baru, kol_seb, kol_k, x;
    private long Backpress;
    private Toast BackToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        hasil_score = findViewById(R.id.hasil_score);
        score_benar = findViewById(R.id.score_benar);
        score_salah = findViewById(R.id.score_salah);
        soal_baru = findViewById(R.id.soal_baru);
        kol_seb = findViewById(R.id.kol_seb);
        kol_k = findViewById(R.id.kol_k);
        x = findViewById(R.id.x);

        int nilai = getIntent().getExtras().getInt("nilai");
        int benar = getIntent().getExtras().getInt("benar");
        int salah = getIntent().getExtras().getInt("salah");

        score_benar.setText("Benar: " + benar);
        score_salah.setText("Salah: " + salah);
        hasil_score.setText(String.valueOf(nilai));


        soal_baru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent back = new Intent(getApplicationContext(), Level.class);
                startActivity(back);
                finish();
            }
        });
//Tombol Sebelumnya
        kol_seb.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent seb = new Intent(getApplicationContext(), Lobby.class);
                startActivity(seb);
                finish();
            }
        });

        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Xnx = new Intent(getApplicationContext(), X.class);
                startActivity(Xnx);
                finish();
            }
        });
//Tombol Keluar
        kol_k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ext = new Intent(Intent.ACTION_MAIN);
                ext.addCategory(Intent.CATEGORY_HOME);
                ext.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                ext.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(ext);
            }
        });
    }

}