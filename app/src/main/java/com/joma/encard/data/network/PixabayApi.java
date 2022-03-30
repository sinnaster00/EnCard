package com.joma.encard.data.network;

import com.joma.encard.data.model.pixabayModel.Hit;
import com.joma.encard.data.model.pixabayModel.PixabayResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PixabayApi {
    @GET("/api")
    Call<PixabayResponse<Hit>> getImage(@Query("key") String apiKey,
                                        @Query("q") String keyWord);
}
