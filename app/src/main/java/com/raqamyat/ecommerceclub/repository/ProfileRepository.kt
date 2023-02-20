package com.raqamyat.ecommerceclub.repository

import com.raqamyat.ecommerceclub.entities.*
import com.raqamyat.ecommerceclub.network.ApiService
import okhttp3.MultipartBody
import javax.inject.Inject

class ProfileRepository@Inject constructor (private val  apiService: ApiService) {

   suspend fun uploadProfileImage(image: MultipartBody.Part): APIResponse<UserModel> {
       return apiService.uploadProfileImage(image)
    }

   suspend fun updateProfile(params: AccountInfoParams): APIResponse<UserModel> {
       return apiService.updateProfile(params)
    }

   suspend fun updatePassword(params: UpdatePasswordParams): APIResponse<UserModel> {
       return apiService.updatePassword(params)
    }


}