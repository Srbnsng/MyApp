package com.android.myapp.room.remote

import com.android.myapp.core.Api
import com.android.myapp.room.Item
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

object ItemApi {

    interface Service{
        @GET("/api/item/items")
        suspend fun find(): List<Item>
        @GET("/api/items/{id}")
        suspend fun read(@Path("id") itemId: String): Item
    }

    val service: Service = Api.retrofit.create(Service::class.java)
}