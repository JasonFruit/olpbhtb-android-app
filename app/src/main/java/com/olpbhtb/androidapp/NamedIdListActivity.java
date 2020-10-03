package com.olpbhtb.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

class NamedIdButtonHandler implements View.OnClickListener {

    private DatabaseAccess db;
    private NamedIdListActivity ctx;

    @Override
    public void onClick(View v) {
        Button btn = (Button)v;
        int authorId = btn.getId();

        Intent intent = new Intent(ctx, HymnListActivity.class);
        Bundle b = new Bundle();
        b.putInt("id", authorId);
        b.putString("id-type", ctx.IdType);
        intent.putExtras(b);
        ctx.startActivity(intent);
    }

    public NamedIdButtonHandler(NamedIdListActivity ctx, DatabaseAccess db) {
        this.ctx = ctx;
        this.db = db;
    }
}

public class NamedIdListActivity extends AppCompatActivity {

    private List<NamedIdable> authors;
    public String IdType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);

        Bundle bun = getIntent().getExtras();
        if(bun != null) {
            this.IdType = bun.getString("id-type");
        }

        DatabaseAccess db = DatabaseAccess.getInstance(this);
        db.open();

        List<NamedIdable> items = new ArrayList<>();

        switch (this.IdType) {
            case "author":
                for (HymnAuthor auth : db.getAuthors()) {
                    items.add(auth);
                }
                break;
            case "meter":
                for (Meter m: db.getMeters()) {
                    items.add(m);
                }
                break;
            case "category":
                for (Category cat: db.getCategories()) {
                    items.add(cat);
                }
                break;
        }

        LinearLayout layout = findViewById(R.id.authorListLayout);

        NamedIdButtonHandler handler = new NamedIdButtonHandler(this, db);

        for (NamedIdable item: items) {
            Button b = new Button(this);
            b.setText(item.toNamedId().Name);
            b.setId(item.toNamedId().Id);
            b.setOnClickListener(handler);
            layout.addView(b);

        }
    }
}