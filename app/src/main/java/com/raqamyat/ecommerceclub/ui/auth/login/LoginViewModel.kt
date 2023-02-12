package com.raqamyat.ecommerceclub.ui.auth.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raqamyat.ecommerceclub.entities.APIResponse
import com.raqamyat.ecommerceclub.entities.UserModel
import com.raqamyat.ecommerceclub.entities.LoginParams
import com.raqamyat.ecommerceclub.repository.AuthorizationRepository
import com.raqamyat.ecommerceclub.utilities.JsonHelper.isHttpException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val auth : AuthorizationRepository
): ViewModel() {
     val loginResponse: MutableLiveData<APIResponse<UserModel>?> = MutableLiveData()
    var errorMessage: MutableLiveData<String?> = MutableLiveData()

    fun loginRequest(loginParams: LoginParams){
        viewModelScope.launch{
            try {
                loginResponse.value = auth.login(loginParams)
            } catch (e: Throwable){
                Log.e("ViewModelError", e.toString())
                errorMessage.value = isHttpException(e)
            }
        }
    }

}