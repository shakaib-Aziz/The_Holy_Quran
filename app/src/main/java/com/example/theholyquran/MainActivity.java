package com.example.theholyquran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    QDH Quran;
    EditText editText;
    Button search;
    ListView listView;

    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Quran=new QDH();

        editText=findViewById(R.id.editTextMain);
        search=findViewById(R.id.searchMain);

        ArrayList<String> arrayList=Quran.getSurahNames();

        listView = findViewById(R.id.listViewMain);

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, activity_SurahDetails.class);
                intent.putExtra("Surah", Integer.toString(position)); // Add extras if needed
                startActivity(intent);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int surahNum=Integer.parseInt(editText.getText().toString());

                if (surahNum>0 && surahNum<=114)
                {
                    listView.setSelection(++surahNum);
                }
                else{
                    Toast.makeText(MainActivity.this, "Surah number is Invalid!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
