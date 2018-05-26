package ru.testapp.major.app;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    //получить данные для отображения
    @GET("androids")
    Call<String> getData();
}
