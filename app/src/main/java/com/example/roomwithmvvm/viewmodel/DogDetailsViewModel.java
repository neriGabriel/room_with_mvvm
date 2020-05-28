package com.example.roomwithmvvm.viewmodel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.roomwithmvvm.model.Imagem;
import com.example.roomwithmvvm.retrofit.RetrofitConfig;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DogDetailsViewModel extends ViewModel {

    private final RetrofitConfig retrofitConfig;

    public DogDetailsViewModel() {
        this.retrofitConfig = new RetrofitConfig();
    }

    private MutableLiveData<List<Imagem>> imagem = new MutableLiveData<>();

    public MutableLiveData<List<Imagem>> getImagem (int dogId) {
        if(this.imagem.getValue() == null) {
            retrofitConfig.getImageAPI().getImageByDogId(dogId).enqueue(new Callback<List<Imagem>>() {
                @Override
                public void onResponse(Call<List<Imagem>> call, Response<List<Imagem>> response) {
                    imagem.setValue(response.body());
                }

                @Override
                public void onFailure(Call<List<Imagem>> call, Throwable t) {
                    imagem.setValue(null);
                }
            });
        }
        return imagem;
    }
}
