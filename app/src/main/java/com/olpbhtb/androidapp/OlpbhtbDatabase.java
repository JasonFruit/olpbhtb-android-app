package com.olpbhtb.androidapp;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class OlpbhtbDatabase extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "hymnal.sqlite3";
    private static final int DATABASE_VERSION = 1;

    public OlpbhtbDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

}
