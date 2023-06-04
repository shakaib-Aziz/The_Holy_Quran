package com.example.theholyquran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class activity_SurahDetails extends AppCompatActivity {

    QDH Quran;
    EditText editText;
    Button search;
    TextView Surah;
    ListView listView;

    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surah_details);

        Quran=new QDH();

        editText=findViewById(R.id.editTextAyat);
        search=findViewById(R.id.searchAyat);
        Surah=findViewById(R.id.SurahNum);

        Intent intent=getIntent();

        int SurahNum= Integer.parseInt(intent.getStringExtra("Surah"));

        Surah.setText(Quran.getSurahName(SurahNum));


    }
}