package com.example.roomwithmvvm.retrofit;

import com.example.roomwithmvvm.model.Dog;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DogAPI {
    @GET("breeds/")
    Observable<List<Dog>> getAllDogs();

    @GET("breeds/{id}")
    Observable<Dog> getDogById(@Path("id") int id);
}
