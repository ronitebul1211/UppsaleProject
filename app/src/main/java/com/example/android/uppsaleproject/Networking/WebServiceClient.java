package com.example.android.uppsaleproject.Networking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebServiceClient {

    private static WebServiceClient webServiceClient = new WebServiceClient();
    private final UppSaleService uppSaleService;


    /*
    init retrofit with upp sale service
    */
    private WebServiceClient(){
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl("https://posapi.uppsale.com/api/")
                .addConverterFactory(GsonConverterFactory.create());

        uppSaleService = retrofitBuilder.build().create(UppSaleService.class);
    }


    public static WebServiceClient getWebServiceClient(){
        return webServiceClient;
    }

    public UppSaleService getUppSaleService(){
        return uppSaleService;
    }

}
