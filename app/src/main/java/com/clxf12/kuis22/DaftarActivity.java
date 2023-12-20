package com.clxf12.kuis22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DaftarActivity extends AppCompatActivity {

    private EditText nama, jenisK,userId,sandi;
    private ImageView sv_daf, bck_daf;
    private TextView tsvdafxt;
    private CheckBox checkbox;

    private DatabaseReference database;

    boolean pasShow;


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

        nama = findViewById(R.id.ktxtnamadaf);
        jenisK = findViewById(R.id.ktxtjnskdaf);
        userId = findViewById(R.id.ktxtuserdaf);
        sandi = findViewById(R.id.ktxtpassdaf);
        sv_daf = findViewById(R.id.sv_daf);
        tsvdafxt = findViewById(R.id.tsvdafxt);
        bck_daf = findViewById(R.id.bck_daf);

        database = FirebaseDatabase.getInstance().getReferenceFromUrl("https://kuis-22-default-rtdb.firebaseio.com/");


        sv_daf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = nama.getText().toString();
                String gender = jenisK.getText().toString();
                String username = userId.getText().toString();
                String password = sandi.getText().toString();

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

                }

            }
        });
        sandi.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                final int Right=2;
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(event.getRawX()>=sandi.getRight()-sandi.getCompoundDrawables()[Right].getBounds().width()){
                        int selection=sandi.getSelectionEnd();
                        if(pasShow){
                            //Atur gambar drawable
                            sandi.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.pass_hidden,0);
                            //Hilangkan Password
                            sandi.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            pasShow=false;
                        }else {
                            //Atur gambar drawable
                            sandi.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.pass_show,0);
                            //Tampilkan Password
                            sandi.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            pasShow=true;
                        }
                        sandi.setSelection(selection);
                        return true;
                    }
                }


                return false;
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