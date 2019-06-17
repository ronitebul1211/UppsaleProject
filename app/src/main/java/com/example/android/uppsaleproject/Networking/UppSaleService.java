package com.example.android.uppsaleproject.Networking;

import com.example.android.uppsaleproject.model.UserDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface UppSaleService {

    @GET("User")
    Call<UserDetails> getUserDetails(@Header("Authorization") String token);
}
