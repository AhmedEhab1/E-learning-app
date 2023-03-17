package com.raqamyat.ecommerceclub.network

import com.raqamyat.ecommerceclub.entities.*
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @POST("login")
    suspend fun login(@Body request: LoginParams?) : APIResponse<UserModel>

    @POST("register")
    suspend fun register(@Body request: RegistrationParams?) : APIResponse<UserModel>

    @POST("profile/update-profile")
    suspend fun updateProfile(@Body request: AccountInfoParams?) : APIResponse<UserModel>

    @POST("profile/update-password")
    suspend fun updatePassword(@Body request: UpdatePasswordParams?) : APIResponse<UserModel>

    @POST("forget-password/send-code")
    suspend fun sendEmailCode(@Body request: ForgetPasswordModel?) : APIResponse<ForgetPasswordModel>

    @POST("forget-password/confirm-code")
    suspend fun confirmEmailCode(@Body request: ForgetPasswordModel?) : APIResponse<ForgetPasswordModel>

    @POST("lessons/update")
    suspend fun updateLesson(@Body request: UpdateLessonParams?) : APIResponse<LastEpisode>

    @POST("forget-password/rest-password")
    suspend fun resetPassword(@Body request: ResetPasswordModel?) : APIResponse<Any>

    @GET("welcome")
    suspend fun getOnBoardingData() : APIResponse<List<WelcomeModel>>

    @GET("home")
    suspend fun getHome() : APIResponse<HomeModel>

    @GET("lessons")
    suspend fun getLessons() : APIResponse<List<LastEpisode>>

    @Multipart
    @POST("profile/update-image")
    suspend fun uploadProfileImage(@Part image: MultipartBody.Part): APIResponse<UserModel>
}