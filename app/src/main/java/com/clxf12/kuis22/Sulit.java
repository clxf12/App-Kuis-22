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

public class Sulit extends AppCompatActivity {

    private ImageView ez_next, ez_back, ez_save;
    private TextView SoalSulit, number;
    private RadioGroup radio_grup;
    private RadioButton pilihanA, pilihanB, pilihanC, pilihanD;
    int nomor = 0;
    int benar = 0, salah = 0;
    int score;


    String Soal[]= new String[]{
            "Apa nama planet ketujuh dari matahari ?",
            "Negara Maroko terletak pada benua apa ?",
            "Apa nama Ibukota Provinsi Jawa Barat ?",
            "Apa nama ilmu yang mempelajari planet dan benda langit yang ada di luar angkasa ?",
            "Landas kontinen laut Indonesia adalah ?",
            "Berapa lama periode waktu yang dibutuhkan bulan untuk mengorbit bumi satu kali secara penuh ?",
            "Hewan yang memiliki kemampuan metamorfosis secara sempurna ?",
            "Apa nama benua yang terletak di sebelah selatan Samudra Hindia ?",
            "Mata uang sah yang digunakan di negara Jepang adalah ?",
            "Siapa nama ilmuwan yang merumuskan teori relativitas ?"
    };

    String Pilihan[]= new String[]{
            "Neptunus","Jupiter","Uranus","Mars",
            "Benua Amerika","Benua Eropa","Benua Afrika","Benua Asia Tenggara",
            "Surabaya","Bandung","Semarang","Madura",
            "Astronomi","Biologi","Antropologi","Fisika",
            "100 m","200 m","300 m","400 m",
            "26 hari","25,4 hari","27,3 hari","30 hari",
            "Laba-laba","Kupu-kupu","Belalang","Semut",
            "Benua Antartika","Benua Asia Tenggara","Benua Australia","Benua Afrika",
            "Ruppe","Ringgit","Yen","Dolar",
            "Alexander Graham Bell","Thomas Edison","Albert Einstein","Alessandro Volta"
    };

    String Benar[]= new String[]{
            "Uranus",
            "Benua Afrika",
            "Bandung",
            "Astronomi",
            "200 m",
            "27,3 hari",
            "Kupu-kupu",
            "Benua Antartika",
            "Yen",
            "Albert Einstein"
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
        setContentView(R.layout.activity_sulit);

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
        ez_save = findViewById(R.id.ez_save);
        SoalSulit = findViewById(R.id.SoalSulit);
        radio_grup = findViewById(R.id.radio_grup);
        pilihanA = findViewById(R.id.pilihanA);
        pilihanB = findViewById(R.id.pilihanB);
        pilihanC = findViewById(R.id.pilihanC);
        pilihanD = findViewById(R.id.pilihanD);
        number = findViewById(R.id.number);


        radio_grup.check(0);

        SoalSulit.setText(Soal[nomor]);
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
                        SoalSulit.setText(Soal[nomor]);

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
                    Toast.makeText(Sulit.this, "Silahkan Pilih Jawaban terlebih Dahulu!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ez_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (nomor < Soal.length) {
                    SoalSulit.setText(Soal[(nomor / 4)]);

                    pilihanA.setText(Pilihan[(nomor / 4) + 0]);
                    pilihanB.setText(Pilihan[(nomor / 4) + 1]);
                    pilihanC.setText(Pilihan[(nomor / 4) + 2]);
                    pilihanD.setText(Pilihan[(nomor / 4) + 3]);

                }
            }
        });

        ez_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Jawaban Tersimpan", Toast.LENGTH_SHORT).show();
            }
        });

    }

}