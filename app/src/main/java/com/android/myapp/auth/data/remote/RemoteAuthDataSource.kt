package com.android.myapp.auth.data.remote

import com.android.myapp.auth.data.TokenHolder
import com.android.myapp.auth.data.User
import com.android.myapp.core.Api
import com.android.myapp.core.Result
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

import java.lang.Exception

object RemoteAuthDataSource {
    interface AuthService {
        @Headers("Content-Type: application/json")
        @POST("/api/auth/login")
        suspend fun login(@Body user: User): TokenHolder
    }
    private val authService: AuthService = Api.retrofit.create(AuthService::class.java)

    suspend fun login(user: User): Result<TokenHolder> {
        return try {
            Result.Success(authService.login(user))
        } catch(e: Exception) {
            Result.Error(e)
        }
    }
}

