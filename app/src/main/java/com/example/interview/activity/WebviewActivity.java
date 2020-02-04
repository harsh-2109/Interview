package com.example.interview.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.interview.R;

public class WebviewActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        WebView view = findViewById(R.id.webview);
        view.loadUrl("https://www.mocky.io/v2/5e334c2232000069df94d504");
        Button btn = findViewById(R.id.webview_btn_ok);

        final SharedPreferences preferences = getPreferences(MODE_PRIVATE);

        if (preferences.getBoolean("T_CAccepted", false)) {
            Intent i = new Intent(WebviewActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_OK);

                //MainActivity Intent
                Intent i = new Intent(WebviewActivity.this, MainActivity.class);
                startActivity(i);

                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("T_CAccepted", true);
                editor.apply();

                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
