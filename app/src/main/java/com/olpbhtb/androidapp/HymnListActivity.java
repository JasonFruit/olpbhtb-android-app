package com.olpbhtb.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

class HymnListButtonHandler implements View.OnClickListener {

    private DatabaseAccess db;
    private HymnListActivity ctx;

    @Override
    public void onClick(View v) {
        Button btn = (Button)v;
        int hymnId = btn.getId();

        TextView tv = ctx.findViewById(R.id.hymnListHeader);

        Intent intent = new Intent(ctx, HymnViewActivity.class);
        Bundle b = new Bundle();
        b.putInt("hymnId", hymnId);
        intent.putExtras(b);
        ctx.startActivity(intent);

    }

    public HymnListButtonHandler(HymnListActivity ctx, DatabaseAccess db) {
        this.ctx = ctx;

        this.db = db;
    }
}


public class HymnListActivity extends AppCompatActivity {

    public List<HymnInfo> Hymns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hymn_list);

        Bundle b = getIntent().getExtras();
        int id = 0;
        String idType = "";
        if(b != null) {
            id = b.getInt("id");
            idType = b.getString("id-type");
        }

        DatabaseAccess db = new DatabaseAccess(this);
        db.open();

        switch (idType) {
            case "author":
                this.Hymns = db.getHymns(new HymnAuthor(id, "Dummy name"));
                break;
            case "meter":
                this.Hymns = db.getHymns(new Meter(id, "Dummy name"));
                break;
            case "category":
                this.Hymns = db.getHymns(new Category(id, "Dummy name"));
                break;
            case "first-lines":
                this.Hymns = db.getHymns();
                break;
        }


        LinearLayout layout = findViewById(R.id.hymnListLayout);

        HymnListButtonHandler handler = new HymnListButtonHandler(this, db);

        for (HymnInfo hi: this.Hymns) {
            Button btn = new Button(this);
            btn.setText(String.valueOf(hi.OlpbhtbNum) + ": " + hi.FirstLine);
            btn.setId(hi.Id);
            btn.setOnClickListener(handler);
            layout.addView(btn);
        }
    }
}