package com.example.singin_belosludtseva.datas.apis;

import android.os.AsyncTask;

import com.example.singin_belosludtseva.datas.common.CheckInternet;
import com.example.singin_belosludtseva.domains.apis.MyAsyncTask;
import com.example.singin_belosludtseva.domains.callbacks.MyResponseCallback;
import com.example.singin_belosludtseva.domains.models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
public class UserCreate extends AsyncTask<Void, Void, String> {
    private final User user;
    private final CheckInternet checkInternet;
    private final MyResponseCallback callback;

    public UserCreate(User user, CheckInternet checkInternet, MyResponseCallback callback) {
        this.user = user;
        this.checkInternet = checkInternet;
        this.callback = callback;
    }

    @Override
    protected String doInBackground(Void... voids) {
        if (!checkInternet.isWiFiConnection() && !checkInternet.isMobileConnection()){
            return "error:Нет подключения к интернету";
        }

        try {
            String url = "http://10.111.20.114:5000/api/user/create";

            String jsonData = new Gson().toJson(user);

            Connection.Response response = Jsoup.connect(url)
                    .ignoreContentType(true)
                    .ignoreHttpErrors(true)
                    .method(Connection.Method.POST)
                    .header("Accept", "application/json")
                    .header("X-Requested-With", "XMLHttpRequest")
                    .header("Content-Type", "application/json")
                    .requestBody(jsonData)
                    .timeout(15000)
                    .execute();

            return response.statusCode() == 200 || response.statusCode() == 201
                    ? response.body()
                    : "error:" + response.body();

        } catch (IOException e) {
            return "error:" + e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (result.startsWith("error:")) {
            callback.onError(result.substring(6));
        } else {
            callback.onComplete(result);
        }
    }
}