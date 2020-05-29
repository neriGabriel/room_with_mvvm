package com.example.roomwithmvvm.retrofit;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {
    private final Retrofit config;

    private String URL = "https://api.thedogapi.com/v1/";

    public RetrofitConfig() {
        this.config = new Retrofit.Builder()
                                  .baseUrl(this.URL)
                                  .addConverterFactory(JacksonConverterFactory.create())
                                  .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                                  .build();
    }

    public DogAPI getDogAPI() {
        return this.config.create(DogAPI.class);
    }

    public ImagemAPI getImageAPI() {
        return this.config.create(ImagemAPI.class);
    }
}

