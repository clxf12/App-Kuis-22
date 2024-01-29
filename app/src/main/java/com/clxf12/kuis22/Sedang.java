package com.clxf12.kuis22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Sedang extends AppCompatActivity {

    private ImageView ez_next, ez_back;
    private TextView SoalSedang, number;
    private RadioGroup radio_grup;
    private RadioButton pilihanA, pilihanB, pilihanC, pilihanD;
    int nomor = 0;
    int benar = 0, salah = 0;
    int score;


    String Soal[]= new String[]{
            "Anjing Pitbull berasal dari negara ?",
            "Penemu Radio berasal dari negara ?",
            "Kota paling boros listrik di Asia adalah ?",
            "Siapa pemegang kekuasaan eksekutif di Indonesia ?",
            "Siapa wakil Presiden Indonesia ke 2 ?",
            "Apa nama planet terdekat dari Matahari ?",
            "Berapa banyak benua di dunia?",
            "Apa nama benua yang dijuluki \"Benua Es\"?",
            "Apa warna bendera negara Amerika Serikat?",
            "Siapa nama Presiden ketiga Indonesia?"
    };

    String Pilihan[]= new String[]{
            "Jerman", "Amerika Serikat", "Inggris", "Belanda",
            "Italia", "Prancis", "China", "Rusia",
            "Singapura", "Indonesia", "India", "Tokyo",
            "Presiden dan Wakil Presiden", "Perdana Menteri", "MPR", "DPR",
            "Hamengkubuwana IX", "Adam Malik", "B. J. Habibie", "Hamzah Haz",
            "Mars", "Merkurius", "Jupiter", "Bumi",
            "Tujuh", "Delapan", "Sembilan", "Sepuluh",
            "Benua Amerika", "Benua Asia", "Benua Eropa", "Benua Antartika",
            "Merah, Putih, dan Biru", "Merah dan Putih", "Hijau, Kuning dan Hitam", "Biru dan Merah",
            "Jokowi", "B. J. Habibie", "Megawati", "Soeharto"
    };

    String Benar[]= new String[]{
            "Inggris",
            "Italia",
            "Tokyo",
            "Presiden dan Wakil Presiden",
            "Hamengkubuwana IX",
            "Merkurius",
            "Tujuh",
            "Benua Antartika",
            "Merah, Putih, dan Biru",
            "B. J. Habibie"
    };

    String num[]= new String[]{
            "1.",
            "2.",
            "3.",
            "4.",
            "5.",
            "6.",
            "7.",
            "8.",
            "9.",
            "10."

    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sedang);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        ez_next = findViewById(R.id.ez_next);
        ez_back = findViewById(R.id.ez_back);
        SoalSedang = findViewById(R.id.SoalSedang);
        radio_grup = findViewById(R.id.radio_grup);
        pilihanA = findViewById(R.id.pilihanA);
        pilihanB = findViewById(R.id.pilihanB);
        pilihanC = findViewById(R.id.pilihanC);
        pilihanD = findViewById(R.id.pilihanD);
        number = findViewById(R.id.number);

        radio_grup.check(0);

        SoalSedang.setText(Soal[nomor]);
        pilihanA.setText(Pilihan[0]);
        pilihanB.setText(Pilihan[1]);
        pilihanC.setText(Pilihan[2]);
        pilihanD.setText(Pilihan[3]);


        ez_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pilihanA.isChecked() || pilihanB.isChecked() || pilihanC.isChecked() || pilihanD.isChecked()) {

                    RadioButton Pilihan_User = findViewById(radio_grup.getCheckedRadioButtonId());
                    String Jawaban_User = Pilihan_User.getText().toString();
                    radio_grup.check(0);

                    if (Jawaban_User.equalsIgnoreCase(Benar[nomor])) {
                        benar++;
                    } else {
                        salah++;
                    }
                    nomor++;
                    if (nomor < Soal.length) {
                        SoalSedang.setText(Soal[nomor]);

                        number.setText(num[nomor]);
                        pilihanA.setText(Pilihan[(nomor * 4) + 0]);
                        pilihanB.setText(Pilihan[(nomor * 4) + 1]);
                        pilihanC.setText(Pilihan[(nomor * 4) + 2]);
                        pilihanD.setText(Pilihan[(nomor * 4) + 3]);

                    } else {
                        score = benar * 10;
                        Intent next = new Intent(getApplicationContext(), Score.class);
                        next.putExtra("nilai", score);
                        next.putExtra("benar", benar);
                        next.putExtra("salah", salah);
                        startActivity(next);

                    }
                } else {
                    Toast.makeText(Sedang.this, "Silahkan Pilih Jawaban terlebih Dahulu!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ez_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (nomor < Soal.length) {
                    SoalSedang.setText(Soal[(nomor / 4)]);

                    pilihanA.setText(Pilihan[(nomor / 4) + 0]);
                    pilihanB.setText(Pilihan[(nomor / 4) + 1]);
                    pilihanC.setText(Pilihan[(nomor / 4) + 2]);
                    pilihanD.setText(Pilihan[(nomor / 4) + 3]);

                }
            }
        });

    }

}