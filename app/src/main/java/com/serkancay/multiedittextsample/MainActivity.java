package com.serkancay.multiedittextsample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // MultiEditText referansı
    MultiEditText multiEditText;
    // Geri dönecek String veriler için liste
    List<String> veriler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Nesnemizi oluşturduk. Aldığı parametreler
        // ilki context, ikincisi yerleşimin Id değeri, üçüncüsü editText için ipucu
        // Bu adımda ilk EditText'imiz ekranda gelecektir.
        // Daha sonra veri girişi oldukça yeni editTextler göreceğiz.
        multiEditText = new MultiEditText(this, R.id.linearLayout, "Liste elemanı");


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                veriler = new ArrayList<String>();
                // Verilerimizi nesnemizden alıyoruz.
                veriler = multiEditText.getStringData();
                // Listemizi tarayıp bir Log basalım.
                for(int i=0; i<veriler.size(); i++){
                    Log.d("MainActivity", (i+1) + ".Eleman: " + veriler.get(i));
                }
            }
        });


    }
}
