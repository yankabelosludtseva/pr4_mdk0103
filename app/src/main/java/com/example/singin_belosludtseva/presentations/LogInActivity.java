package com.example.singin_belosludtseva.presentations;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.singin_belosludtseva.R;
import com.example.singin_belosludtseva.datas.apis.UserLogin;
import com.example.singin_belosludtseva.datas.common.CheckInternet;
import com.example.singin_belosludtseva.domains.callbacks.MyResponseCallback;
import com.example.singin_belosludtseva.domains.models.User;

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

            RequestUserLogin(email, password);
        });
    }

    public void RequestUserLogin(String email, String password) {
        Context context = this;

        CheckInternet checkInternet = new CheckInternet(this);

        User user = new User();
        user.email = email;
        user.password = password;

        UserLogin RequestUserLogin = new UserLogin(
                user,
                checkInternet,
                new MyResponseCallback() {

                    @Override
                    public void onComplete(String result) {
                        Log.d("USER_LOGIN", result);
                        Toast.makeText(context, "Успешная авторизация пользователя", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(String error) {
                        Log.d("USER_LOGIN", error);
                        Toast.makeText(context, "При авторизации возникли ошибки", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        RequestUserLogin.execute();
    }
}