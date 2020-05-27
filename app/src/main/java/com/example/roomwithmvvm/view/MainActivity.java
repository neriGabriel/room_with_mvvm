package com.example.roomwithmvvm.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.roomwithmvvm.R;
import com.example.roomwithmvvm.databinding.ActivityMainBinding;
import com.example.roomwithmvvm.model.Dog;
import com.example.roomwithmvvm.retrofit.RetrofitConfig;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
   /*private RetrofitConfig retrofitConfig;
    private Call<List<Dog>> request;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());

        /*this.retrofitConfig = new RetrofitConfig();

        this.request = retrofitConfig.getDogAPI().getAllDogs();
        this.request.enqueue(new Callback<List<Dog>>() {
            @Override
            public void onResponse(Call<List<Dog>> call, Response<List<Dog>> response) {
                Log.d("MainActivity", response.body().toString());
            }

            @Override
            public void onFailure(Call<List<Dog>> call, Throwable t) {
                Log.d("MainActivity", t.toString());
            }
        });*/
    }
}
