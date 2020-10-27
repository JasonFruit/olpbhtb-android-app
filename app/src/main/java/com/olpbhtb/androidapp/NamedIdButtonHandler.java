package com.olpbhtb.androidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NamedIdButtonHandler implements View.OnClickListener {

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
