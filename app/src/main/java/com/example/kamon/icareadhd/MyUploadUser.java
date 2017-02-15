package com.example.kamon.icareadhd;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * Created by masterUNG on 2/12/2017 AD.
 */

public class MyUploadUser extends AsyncTask<Void, Void, String>{

    private Context context;
    private static final String urlPHP = "http://www.icareadhd.inw.mn.122.155.18.18.no-domain.name/add_iCare.php";
    private String userString, passString, typeString,
            nameString, surnameString, timeString;

    public MyUploadUser(Context context,
                        String userString,
                        String passString,
                        String typeString,
                        String nameString,
                        String surnameString,
                        String timeString) {
        this.context = context;
        this.userString = userString;
        this.passString = passString;
        this.typeString = typeString;
        this.nameString = nameString;
        this.surnameString = surnameString;
        this.timeString = timeString;
    }

    @Override
    protected String doInBackground(Void... voids) {

        try {

            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("isAdd", "true")
                    .add("Username", userString)
                    .add("Password", passString)
                    .add("Type", typeString)
                    .add("Firstname", nameString)
                    .add("Lastname", surnameString)
                    .add("Timestamp", timeString)
                    .build();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(urlPHP).post(requestBody).build();
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();

        } catch (Exception e) {
            Log.d("12febV1", "e doin ==> " + e.toString());
            return null;
        }


    }
}   // Main Class
