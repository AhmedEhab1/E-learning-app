package com.raqamyat.ecommerceclub.network

import com.raqamyat.ecommerceclub.entities.*
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @POST("login")
    suspend fun login(@Body request: LoginParams?) : APIResponse<UserModel>

    @POST("register")
    suspend fun register(@Body request: RegistrationParams?) : APIResponse<UserModel>

    @POST("forget-password/send-code")
    suspend fun sendEmailCode(@Body request: ForgetPasswordModel?) : APIResponse<ForgetPasswordModel>

    @POST("forget-password/confirm-code")
    suspend fun confirmEmailCode(@Body request: ForgetPasswordModel?) : APIResponse<ForgetPasswordModel>

    @POST("forget-password/rest-password")
    suspend fun resetPassword(@Body request: ResetPasswordModel?) : APIResponse<Any>

    @Multipart
    @POST("profile/update-image")
    suspend fun uploadProfileImage(@Part image: MultipartBody.Part): APIResponse<UserModel>
}