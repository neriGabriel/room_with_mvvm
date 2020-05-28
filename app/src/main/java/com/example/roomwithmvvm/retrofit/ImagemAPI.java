package com.example.roomwithmvvm.retrofit;

import com.example.roomwithmvvm.model.Imagem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ImagemAPI {
    @GET("images/search")
    Call<List<Imagem>> getImageByDogId(@Query("breed_ids") int id);
}
