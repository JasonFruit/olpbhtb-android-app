package com.olpbhtb.androidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HymnListButtonHandler implements View.OnClickListener {

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
