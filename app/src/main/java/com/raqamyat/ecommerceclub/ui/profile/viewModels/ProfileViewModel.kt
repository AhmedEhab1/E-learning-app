package com.raqamyat.ecommerceclub.ui.profile.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raqamyat.ecommerceclub.entities.APIResponse
import com.raqamyat.ecommerceclub.entities.ForgetPasswordModel
import com.raqamyat.ecommerceclub.entities.ResetPasswordModel
import com.raqamyat.ecommerceclub.entities.UserModel
import com.raqamyat.ecommerceclub.repository.AuthorizationRepository
import com.raqamyat.ecommerceclub.repository.ProfileRepository
import com.raqamyat.ecommerceclub.utilities.JsonHelper.isHttpException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val auth: ProfileRepository
) : ViewModel() {
    val response: MutableLiveData<APIResponse<UserModel>?> = MutableLiveData()
    val logoutResponse: MutableLiveData<APIResponse<Any>?> = MutableLiveData()
    var errorMessage: MutableLiveData<String?> = MutableLiveData()

    fun uploadProfileImage(image: MultipartBody.Part) {
        viewModelScope.launch {
            try {
                response.value = auth.uploadProfileImage(image)
            } catch (e: Throwable) {
                Log.e("ViewModelError", e.toString())
                errorMessage.value = isHttpException(e)
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            try {
                logoutResponse.value = auth.logout()
            } catch (e: Throwable) {
                Log.e("ViewModelError", e.toString())
                errorMessage.value = isHttpException(e)
            }
        }
    }



}