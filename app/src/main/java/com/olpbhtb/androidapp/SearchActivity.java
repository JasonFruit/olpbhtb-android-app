package com.olpbhtb.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {

    public void WordsSearchOnClick(View v) {
        TextView q = findViewById(R.id.q);

        Intent catIntent = new Intent(this, HymnListActivity.class);
        Bundle cb = new Bundle();
        cb.putString("id-type", "words-search");
        cb.putString("q", q.getText().toString());
        catIntent.putExtras(cb);
        startActivity(catIntent);
    }

    public void PhraseSearchOnClick(View v) {
        TextView q = findViewById(R.id.q);

        Intent catIntent = new Intent(this, HymnListActivity.class);
        Bundle cb = new Bundle();
        cb.putString("id-type", "phrase-search");
        cb.putString("q", q.getText().toString());
        catIntent.putExtras(cb);
        startActivity(catIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }
}