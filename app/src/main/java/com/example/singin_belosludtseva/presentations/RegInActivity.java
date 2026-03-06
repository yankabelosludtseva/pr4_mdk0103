package com.example.singin_belosludtseva.presentations;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.singin_belosludtseva.R;
import com.example.singin_belosludtseva.datas.apis.UserCreate;
import com.example.singin_belosludtseva.datas.common.CheckInternet;
import com.example.singin_belosludtseva.domains.callbacks.MyResponseCallback;
import com.example.singin_belosludtseva.domains.models.User;

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

    public void RequestUserCreate(String email, String password, String firstname,
                                  String lastname, String surname, String dateOfBirth, int gender) {
        Context context = this;

        CheckInternet checkInternet = new CheckInternet(this);

        User user = new User();
        user.email = email;
        user.password = password;
        user.firstname = firstname;
        user.lastname = lastname;
        user.surname = surname;
        user.dateOfBirth = dateOfBirth;
        user.gender = gender;

        UserCreate RequestUserCreate = new UserCreate(
                user,
                checkInternet,
                new MyResponseCallback() {

                    @Override
                    public void onComplete(String result) {
                        Log.d("USER_CREATE", result);
                        Toast.makeText(context, "Пользователь успешно зарегистрирован", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(String error) {
                        Log.d("USER_CREATE", error);
                        Toast.makeText(context, "При регистрации возникли ошибки", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        RequestUserCreate.execute();
    }
}