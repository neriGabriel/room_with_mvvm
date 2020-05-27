package com.example.roomwithmvvm.retrofit;

import com.example.roomwithmvvm.model.Dog;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DogAPI {
    @GET("breeds/")
    Call<List<Dog>> getAllDogs();

    @GET("breeds/{id}")
    Call<Dog> getDogById(@Path("id") int id);
}
