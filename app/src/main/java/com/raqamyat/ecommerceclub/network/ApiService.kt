package com.raqamyat.ecommerceclub.network

import com.raqamyat.ecommerceclub.entities.APIResponse
import com.raqamyat.ecommerceclub.entities.UserModel
import com.raqamyat.ecommerceclub.entities.LoginParams
import com.raqamyat.ecommerceclub.entities.RegistrationParams
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    suspend fun login(@Body request: LoginParams?) : APIResponse<UserModel>

    @POST("register")
    suspend fun register(@Body request: RegistrationParams?) : APIResponse<UserModel>
}