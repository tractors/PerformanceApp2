package com.will.performanceapp2.net;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {
    @GET("news/item")
    Call<ResponseBody> getNBANews(@Query("column") String column,
                                  @Query("articleIds") String articleIds);

    @GET(".")
    Call<ResponseBody> getPictures(@Query("key") String key,@Query("q") String keyWord,@Query("per_page") int per_page, @Query("page") int page);
}
