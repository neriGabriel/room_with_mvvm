package com.example.roomwithmvvm.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.roomwithmvvm.model.Dog;
import com.example.roomwithmvvm.retrofit.RetrofitConfig;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DogViewModel extends ViewModel {

    private final RetrofitConfig retrofitConfig;
    private MutableLiveData<List<Dog>> dogList = new MutableLiveData<>();

    public DogViewModel() {
        this.retrofitConfig = new RetrofitConfig();
    }

    public MutableLiveData<List<Dog>> getAllDogs() {
        if(this.dogList.getValue() == null) {
            retrofitConfig.getDogAPI().getAllDogs().enqueue(new Callback<List<Dog>>() {
                @Override
                public void onResponse(Call<List<Dog>> call, Response<List<Dog>> response) {
                    dogList.setValue(response.body());
                }

                @Override
                public void onFailure(Call<List<Dog>> call, Throwable t) {
                    dogList.setValue(null);
                }
            });
        }
        return dogList;
    }

    public MutableLiveData<Dog> getDogById(int id) {
        MutableLiveData<Dog> dog = new MutableLiveData<>();

        retrofitConfig.getDogAPI().getDogById(id).enqueue(new Callback<Dog>() {
            @Override
            public void onResponse(Call<Dog> call, Response<Dog> response) {
                dog.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Dog> call, Throwable t) {
                dog.setValue(new Dog());
            }
        });
        return dog;
    }
}
