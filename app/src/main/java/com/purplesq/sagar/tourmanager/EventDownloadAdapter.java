package com.purplesq.sagar.tourmanager;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class EventDownloadAdapter {
    public static DownloadService getretrofitbuilder(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://s3-ap-southeast-1.amazonaws.com/purplesq/android/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(DownloadService.class);
    }
}
