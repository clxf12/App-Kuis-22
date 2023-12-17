package com.clxf12.kuis22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView koldaf1, kolmsk2, msk12;
    private TextView txtdaf1, txtmsk1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }



        koldaf1 = findViewById(R.id.koldaf1);

        koldaf1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register  = new Intent(getApplicationContext(), DaftarActivity.class);
                startActivity(register);
                finish();
            }
        });

        kolmsk2 = findViewById(R.id.kolmsk2);

        kolmsk2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent masuk = new Intent(getApplicationContext(), MasukActivity.class);
                startActivity(masuk);
                finish();
            }
        });

        msk12 = findViewById(R.id.msk12);

        msk12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Xnx = new Intent(getApplicationContext(), X.class);
                startActivity(Xnx);
            }
        });
    }
}