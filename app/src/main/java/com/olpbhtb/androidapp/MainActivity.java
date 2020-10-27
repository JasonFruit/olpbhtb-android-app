package com.olpbhtb.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.PopupMenu;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
            case R.id.tunes:
                Intent tunesIntent = new Intent(this, NamedIdListActivity.class);
                Bundle tb = new Bundle();
                tb.putString("id-type", "tune");
                tunesIntent.putExtras(tb);
                startActivity(tunesIntent);
                return true;
            case R.id.first_lines:
                Intent firstLinesIntent = new Intent(this, HymnListActivity.class);
                Bundle flb = new Bundle();
                flb.putString("id-type", "first-lines");
                firstLinesIntent.putExtras(flb);
                startActivity(firstLinesIntent);
                return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.main_fab);

        // set the floating action button's image (isn't there a simpler way to set a static image?)
        AssetManager am = getAssets();
        InputStream stream = null;
        try {
            stream = am.open("images/menu.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Bitmap bmp = BitmapFactory.decodeStream(stream);
        fab.setImageBitmap(bmp);

        final Context ctx = this;

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu pm = new PopupMenu(ctx, view);
                pm.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return onOptionsItemSelected(item);
                    }
                });

                pm.inflate(R.menu.main_menu);
                pm.show();

            }
        });
    }
}