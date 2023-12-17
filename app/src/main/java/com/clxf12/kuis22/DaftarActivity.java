package com.clxf12.kuis22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DaftarActivity extends AppCompatActivity {

    private EditText ktxtnamadaf, ktxtjnskdaf, ktxtuserdaf, ktxtpassdaf;
    private ImageView sv_daf, bck_daf;
    private TextView tsvdafxt;

    private DatabaseReference database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        ktxtnamadaf = findViewById(R.id.ktxtnamadaf);
        ktxtjnskdaf = findViewById(R.id.ktxtjnskdaf);
        ktxtuserdaf = findViewById(R.id.ktxtuserdaf);
        ktxtpassdaf = findViewById(R.id.ktxtpassdaf);
        sv_daf = findViewById(R.id.sv_daf);
        tsvdafxt = findViewById(R.id.tsvdafxt);
        bck_daf = findViewById(R.id.bck_daf);

        database = FirebaseDatabase.getInstance().getReferenceFromUrl("https://kuis-22-default-rtdb.firebaseio.com/");

        sv_daf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = ktxtnamadaf.getText().toString();
                String gender = ktxtjnskdaf.getText().toString();
                String username = ktxtuserdaf.getText().toString();
                String password = ktxtpassdaf.getText().toString();

                if (username.isEmpty() || name.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Pastikan Semua Data Terisi!!!", Toast.LENGTH_SHORT).show();
                } else {
                    database = FirebaseDatabase.getInstance().getReference("users");
                    database.child(username).child("name").setValue(name);
                    database.child(username).child("gender").setValue(gender);
                    database.child(username).child("username").setValue(username);
                    database.child(username).child("password").setValue(password);

                    Toast.makeText(getApplicationContext(), "Berhasil Daftar :)", Toast.LENGTH_SHORT).show();
                    Intent register = new Intent(getApplicationContext(), MasukActivity.class);
                    startActivity(register);
                    finish();

                }

            }
        });

        bck_daf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kembali = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(kembali);
            }
        });
    }
}