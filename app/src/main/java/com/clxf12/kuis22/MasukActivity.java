package com.clxf12.kuis22;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MasukActivity extends AppCompatActivity {

    private EditText userId2, sandi2;

    private ImageView nxt_msk, bck_msk;

    private DatabaseReference database;

    boolean pasShow2;


    public static String PREFS_NAME="myPrefsFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masuk);
        userId2 = findViewById(R.id.ktxtusermsk);
        sandi2 = findViewById(R.id.ktxtpasswmsk);
        nxt_msk = findViewById(R.id.nxt_msk);
        bck_msk = findViewById(R.id.bck_msk);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }


        nxt_msk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences(MasukActivity.PREFS_NAME,0);
                SharedPreferences.Editor editor= sharedPreferences.edit();

                editor.putBoolean("hasLoggedIn",true);
                editor.commit();

                String username = userId2.getText().toString();
                String password = sandi2.getText().toString();

                database = FirebaseDatabase.getInstance().getReference("users");

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Username atau Password anda salah!!!", Toast.LENGTH_SHORT).show();
                }else {
                    database.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.child(username).exists()){
                                if (snapshot.child(username).child("password").getValue(String.class).equals(password)){
                                    Toast.makeText(getApplicationContext(), "Login Berhasil", Toast.LENGTH_SHORT).show();
                                    Intent masuk = new Intent(getApplicationContext(), Lobby.class);
                                    startActivity(masuk);
                                    finish();
                                }
                            }else{
                                Toast.makeText(getApplicationContext(), "Belum Terdaftar", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

            }
        });

        bck_msk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kembali = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(kembali);
                finish();
            }
        });

        sandi2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                final int Right=2;
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(event.getRawX()>=sandi2.getRight()-sandi2.getCompoundDrawables()[Right].getBounds().width()){
                        int selection=sandi2.getSelectionEnd();
                        if(pasShow2){
                            //Atur gambar drawable
                            sandi2.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.pass_hidden,0);
                            //Hilangkan Password
                            sandi2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            pasShow2=false;
                        }else {
                            //Atur gambar drawable
                            sandi2.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.pass_show,0);
                            //Tampilkan Password
                            sandi2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            pasShow2=true;
                        }
                        sandi2.setSelection(selection);
                        return true;
                    }
                }


                return false;
            }
        });


    }
}