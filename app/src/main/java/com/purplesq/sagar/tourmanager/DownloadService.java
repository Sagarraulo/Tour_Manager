package com.purplesq.sagar.tourmanager;

import retrofit.http.GET;

public interface DownloadService
{
    @GET("android_workshop_data.json")
    retrofit.Call<Events> fetchEvents();
}















