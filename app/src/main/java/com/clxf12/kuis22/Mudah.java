package com.clxf12.kuis22;

import androidx.activity.OnBackPressedDispatcher;
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

public class Mudah extends AppCompatActivity {

    private ImageView ez_next, ez_back, ez_save;
    private TextView SoalMudah, number;
    private RadioGroup radio_grup;
    private RadioButton pilihanA, pilihanB, pilihanC, pilihanD;
    int nomor = 0;
    int benar = 0, salah = 0;
    int score;

    //-- INPUT SOAL MUDAH --//
    String Soal[]= new String[]{
            "Siapakah Presiden Indonesia yang kedua ?",
            "Apa ibukota Jawa Tengah ?",
            "Monumen terkenal di kota Paris adalah menara ?",
            "Bahan bakar pada kereta api adalah ?",
            "Pusat peredaran tata surya adalah ?",
            "Vitamin yang banyak terkandung dalam buah-buahan adalah ?",
            "Dari mana alat musik tifa berasal ?",
            "Julukan dari Kota Bogor adalah ?",
            "Layar Komputer disebut juga ?",
            "Apa ibu Kota Papua Barat ?"
    };
    //-- PILIHAN JAWABAN --//
    String Pilihan[]= new String[]{
            "Soeharto", "Megawati", "Joko Widodo", "B. J. Habibie",
            "Jakarta", "Ambon", "Semarang", "Surabaya",
            "Patung Liberty", "Tembok Berlin", "Tugu Monas", "Menara Eiffel",
            "Minyak tanah", "Bensin", "Batu Bara", "Gas",
            "Bulan", "Matahari", "Bintang", "Bumi",
            "Vitamin A", "Vitamin B", "Vitamin C", "Vitamin D",
            "Maluku dan Papua", "Sulawesi", "Jawa Timur", "Sumatera Barat",
            "Kota Musik", "Kota Pahlawan", "Kota Hujan", "Kota Batik",
            "Mouse", "Print", "Monitor", "Kursor",
            "Kaimana", "Sorong", "Fak-fak", "Manokwari"
    };
    //-- JAWABAN BENAR --//
    String Benar[]= new String[]{
            "Soeharto",
            "Semarang",
            "Menara Eiffel",
            "Batu Bara",
            "Matahari",
            "Vitamin C",
            "Maluku dan Papua",
            "Kota Hujan",
            "Monitor",
            "Manokwari"
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
        setContentView(R.layout.activity_mudah);

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
        SoalMudah = findViewById(R.id.SoalMudah);
        radio_grup = findViewById(R.id.radio_grup);
        pilihanA = findViewById(R.id.pilihanA);
        pilihanB = findViewById(R.id.pilihanB);
        pilihanC = findViewById(R.id.pilihanC);
        pilihanD = findViewById(R.id.pilihanD);
        number = findViewById(R.id.number);

        radio_grup.check(0);

        SoalMudah.setText(Soal[nomor]);
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
                        SoalMudah.setText(Soal[nomor]);

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
                    Toast.makeText(Mudah.this, "Silahkan Pilih Jawaban terlebih Dahulu!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ez_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (nomor < Soal.length) {
                    SoalMudah.setText(Soal[(nomor / 4)]);

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