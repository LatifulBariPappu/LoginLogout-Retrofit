package com.example.restapis.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class apicontroller {
    private static final String baseUrl="http://192.168.10.166:8080/dashboard/api/";
    private static apicontroller clientobj;
    private static Retrofit retrofit;

    apicontroller(){
        retrofit=new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static synchronized apicontroller getInstance(){
        if(clientobj ==null){
            clientobj =new apicontroller();
        }
        return clientobj;
    }
    public apiset getapi(){
        return retrofit.create(apiset.class);
    }

}
