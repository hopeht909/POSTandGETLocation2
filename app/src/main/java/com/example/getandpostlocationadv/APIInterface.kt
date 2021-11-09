package com.example.getandpostlocationadv

import retrofit2.Call
import retrofit2.http.*



interface APIInterface {
    @Headers("Content-Type: application/json")
    @GET("/test/")
    fun getUser(): Call<List<UsersData.UsersDataItem>>


    @Headers("Content-Type: application/json")
    @POST("/test/")
    fun addUser(@Body userData: UsersData.UsersDataItem): Call<UsersData.UsersDataItem>
}