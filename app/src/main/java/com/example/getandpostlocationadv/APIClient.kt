package com.example.getandpostlocationadv

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// (2) Create a new Kotlin class and name it “APIClient”
// the code content only the link change

class APIClient {
    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit? {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()


        retrofit = Retrofit.Builder()
            .baseUrl("https://dojo-recipes.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit
    }
}