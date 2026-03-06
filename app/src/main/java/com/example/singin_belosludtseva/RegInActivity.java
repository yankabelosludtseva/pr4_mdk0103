package com.example.singin_belosludtseva;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class RegInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reg_in);

        TextView bthOpenLogIn = findViewById(R.id.bth_open_log_in);
        bthOpenLogIn.setOnClickListener(v -> {
            finish();
        });
    }
}