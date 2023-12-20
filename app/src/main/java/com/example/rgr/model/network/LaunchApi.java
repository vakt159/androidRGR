package com.example.rgr.model.network;

import androidx.room.Ignore;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LaunchApi {

    @GET("v3/launches/past")
    @Ignore
    Call<List<LaunchNetworkEntity>> getLaunches();

}
