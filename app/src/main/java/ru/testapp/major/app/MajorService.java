package ru.testapp.major.app;

import retrofit2.Call;

public class MajorService {

    private Api api;

    public MajorService(Api api) {
        this.api = api;
    }

    public Call<String> getData() {
        return api.getData();
    }
}
