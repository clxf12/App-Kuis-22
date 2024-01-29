package com.clxf12.kuis22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

public class Level extends AppCompatActivity {

    private ImageView bck_lvl, sv_lvl, lvltxt1, lvltxt2, lvltxt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        bck_lvl = findViewById(R.id.bck_lvl);
        sv_lvl = findViewById(R.id.sv_lvl);
        lvltxt1 = findViewById(R.id.lvltxt1);
        lvltxt2 = findViewById(R.id.lvltxt2);
        lvltxt3 = findViewById(R.id.lvltxt3);

        bck_lvl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kembali = new Intent(getApplicationContext(), Lobby.class);
                startActivity(kembali);
            }
        });

        sv_lvl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                sv_lvl.getTouchables();
                {
                    Toast.makeText(getApplicationContext(), "Level Dipilih Secara Random :p", Toast.LENGTH_SHORT).show();
                }
                ;


                Intent lanjut = new Intent(getApplicationContext(), Sedang.class);
                startActivity(lanjut);
            }
        });
        //-- LEVEL MUDAH --//
        lvltxt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mudah = new Intent(getApplicationContext(), Mudah.class);
                startActivity(mudah);
                finishAndRemoveTask();
            }
        });
        //-- LEVEL SEDANG --//
        lvltxt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sedang = new Intent(getApplicationContext(), Sedang.class);
                startActivity(sedang);
                finishAndRemoveTask();
            }
        });
        //-- LEVEL SULIT --//
        lvltxt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sulit = new Intent(getApplicationContext(), Sulit.class);
                startActivity(sulit);
                finishAndRemoveTask();
            }
        });

    }
}