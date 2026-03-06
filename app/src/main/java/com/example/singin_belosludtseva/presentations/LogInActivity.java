package com.example.singin_belosludtseva.presentations;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.singin_belosludtseva.R;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        TextView bthOpenSingIn = findViewById(R.id.bth_open_sing_in);
        bthOpenSingIn.setOnClickListener(v -> {
            Intent SingIn = new Intent(LogInActivity.this, RegInActivity.class);
            startActivity(SingIn);
        });

        Button bthLogIn = findViewById(R.id.bth_log_in);
        bthLogIn.setOnClickListener(v -> {
            TextView etEmail = findViewById(R.id.et_email);
            TextView etPassword = findViewById(R.id.et_password);

            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();

            if(email.isEmpty()) {

                Toast.makeText(LogInActivity.this, "Не указана почта пользователя", Toast.LENGTH_SHORT).show();
                return;
            }

            if(password.isEmpty()) {

                Toast.makeText(LogInActivity.this, "Не указан пароль пользователя", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(LogInActivity.this, "Пользователь авторизован", Toast.LENGTH_SHORT).show();
        });
    }
}