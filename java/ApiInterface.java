package com.example.paramedics.myrxjava.NodeJS;


import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ApiInterface {

    @FormUrlEncoded
    @HTTP(method = "PUT", path = "update", hasBody = true) // using @HTTP just to use @FormUrlEncoded + @Field (cause its only usable with @POST)
    Call<Result> updateUser(
            @Field("id") int id,
            @Field("name") String name,
            @Field("age") int age);


    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "delete", hasBody = true) // using @HTTP just to use @FormUrlEncoded + @Field (cause its only usable with @POST)
    Call<Result> deleteUser(
            @Field("id") int id);


    @GET("getfield")
    Single<Data> getUserByField(
            @Query("age") int age);  // add to the link ...?age=age


    @GET("getdata")
    Single<Data> getUsers();


    @FormUrlEncoded
    @POST("add")
    Call<Result> insertUser(
            @Field("name") String name,
            @Field("age") int age);

}

