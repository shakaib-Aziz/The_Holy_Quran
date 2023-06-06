package com.example.theholyquran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class activity_SurahDetails extends AppCompatActivity {

    QDH Quran;
    QuranArabicText ArabicText;
    EditText editText;

    Button search;
    TextView Surah;
    ListView listView;

    ArrayList<String> arrayList=null;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surah_details);

        Quran=new QDH();
        ArabicText=new QuranArabicText();

        editText=findViewById(R.id.editTextAyat);
        search=findViewById(R.id.searchAyat);
        Surah=findViewById(R.id.SurahName);

        Intent intent=getIntent();
        int SurahNum= Integer.parseInt(intent.getStringExtra("Surah"));
        Surah.setText(Quran.getSurahName(SurahNum));



        if (SurahNum!=113) {
            getSurahVerses(Quran.getSurahStart(SurahNum), Quran.getSurahStart(SurahNum + 1));
        }
        else
        {
            getSurahVerses(Quran.getSurahStart(SurahNum), Quran.getSurahStart(SurahNum)+Quran.getSurahVersesCount(SurahNum)+1);
        }

        listView = findViewById(R.id.listViewSurahVerses);

        updateListView();
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String verse = editText.getText().toString();
                if (!verse.isEmpty()) {
                    int verseNum = Integer.parseInt(verse);
                    arrayList.clear();

                    if (verseNum > 0 && verseNum <= Quran.getSurahVersesCount(SurahNum)) {
                        arrayList = ArabicText.GetVerse(Quran.getSurahStart(SurahNum) + verseNum - 1);      //Because array index starts from 0 but user understands it as starting from 1
                    } else {
                        Toast.makeText(activity_SurahDetails.this, "Ayat number is Invalid!", Toast.LENGTH_SHORT).show();
                    }
                    updateListView();
                }
            }
        });
    }
    public void getSurahVerses(int start,int end)
    {
        arrayList=ArabicText.GetData(start,end-1);   //Because I have to be within the constraint not to exceed to the next Surah
    }

    public void updateListView()
    {
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);
    }
}