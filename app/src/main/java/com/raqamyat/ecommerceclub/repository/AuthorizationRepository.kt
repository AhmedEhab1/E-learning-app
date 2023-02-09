package com.raqamyat.ecommerceclub.repository

import com.raqamyat.ecommerceclub.entities.APIResponse
import com.raqamyat.ecommerceclub.entities.UserModel
import com.raqamyat.ecommerceclub.entities.LoginParams
import com.raqamyat.ecommerceclub.entities.RegistrationParams
import com.raqamyat.ecommerceclub.network.ApiService
import javax.inject.Inject

class AuthorizationRepository@Inject constructor (private val  apiService: ApiService) {

   suspend fun login(loginParams: LoginParams): APIResponse<UserModel> {
       return apiService.login(loginParams)
    }

   suspend fun register(loginParams: RegistrationParams): APIResponse<UserModel> {
       return apiService.register(loginParams)
    }
}