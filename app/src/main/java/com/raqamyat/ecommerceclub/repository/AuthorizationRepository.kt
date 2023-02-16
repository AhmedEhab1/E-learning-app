package com.raqamyat.ecommerceclub.repository

import com.raqamyat.ecommerceclub.entities.*
import com.raqamyat.ecommerceclub.network.ApiService
import javax.inject.Inject

class AuthorizationRepository@Inject constructor (private val  apiService: ApiService) {

   suspend fun login(loginParams: LoginParams): APIResponse<UserModel> {
       return apiService.login(loginParams)
    }

   suspend fun register(loginParams: RegistrationParams): APIResponse<UserModel> {
       return apiService.register(loginParams)
    }

    suspend fun sendEmailCode(params: ForgetPasswordModel): APIResponse<ForgetPasswordModel> {
       return apiService.sendEmailCode(params)
    }

    suspend fun confirmEmailCode(params: ForgetPasswordModel): APIResponse<ForgetPasswordModel> {
       return apiService.confirmEmailCode(params)
    }

    suspend fun resetPassword(params: ResetPasswordModel): APIResponse<Any> {
       return apiService.resetPassword(params)
    }
}