package com.example.alarm_doc;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.PUT;

public interface ReadingService {

    @PUT("/")
    Response uploadReading(@Body BITalinoReading reading);

}
