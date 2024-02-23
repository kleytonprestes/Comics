package com.example.comics.data.remote

import com.example.comics.data.model.ItemModel
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("comics")
    suspend fun getComics(
        @Query("ts") ts: String = "1682982412",
        @Query("apikey") apikey: String = "b7e14bab409c70a5c4e7c2b319c09d7b",
        @Query("hash") hash: String = "3482f01e9bf207a437a4b621c91339ad"
    ) : ItemModel
}