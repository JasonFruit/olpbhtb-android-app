package com.olpbhtb.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView tv = findViewById(R.id.aboutlink);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        String linkText = "For more information, visit <a href=\"http://olpbhtb.com\">olpbhtb.com</a>";
        tv.setText(Html.fromHtml(linkText));

    }
}