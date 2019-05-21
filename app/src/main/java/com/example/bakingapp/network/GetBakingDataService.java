package com.example.bakingapp.network;

import com.example.bakingapp.model.Baking;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetBakingDataService {

    @GET("baking.json")
    Call<List<Baking>> getBakingData();
}
