package com.example.restapis.api;

import com.example.restapis.models.signinmodel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface apiset {
    @FormUrlEncoded
    @POST("login.php")
    Call<signinmodel> verifyusers(
            @Field("email") String email,
            @Field("password") String password
    );
}