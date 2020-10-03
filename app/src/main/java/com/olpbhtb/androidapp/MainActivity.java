package com.olpbhtb.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                Intent aboutIntent = new Intent(this, AboutActivity.class);
                startActivity(aboutIntent);
                return true;
            case R.id.authors:
                Intent authorIntent = new Intent(this, NamedIdListActivity.class);
                Bundle ab = new Bundle();
                ab.putString("id-type", "author");
                authorIntent.putExtras(ab);
                startActivity(authorIntent);
                return true;
            case R.id.meters:
                Intent intent = new Intent(this, NamedIdListActivity.class);
                Bundle mb = new Bundle();
                mb.putString("id-type", "meter");
                intent.putExtras(mb);
                startActivity(intent);
                return true;
            case R.id.categories:
                Intent catIntent = new Intent(this, NamedIdListActivity.class);
                Bundle cb = new Bundle();
                cb.putString("id-type", "category");
                catIntent.putExtras(cb);
                startActivity(catIntent);
                return true;
            case R.id.search:
                Intent searchIntent = new Intent(this, SearchActivity.class);
                startActivity(searchIntent);
                return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}