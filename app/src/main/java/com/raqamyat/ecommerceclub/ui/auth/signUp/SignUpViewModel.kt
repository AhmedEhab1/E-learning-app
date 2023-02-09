package com.raqamyat.ecommerceclub.ui.auth.signUp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raqamyat.ecommerceclub.entities.APIResponse
import com.raqamyat.ecommerceclub.entities.UserModel
import com.raqamyat.ecommerceclub.entities.RegistrationParams
import com.raqamyat.ecommerceclub.repository.AuthorizationRepository
import com.raqamyat.ecommerceclub.utilities.JsonHelper.isHttpException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val auth: AuthorizationRepository
) : ViewModel() {
    val response: MutableLiveData<APIResponse<UserModel>?> = MutableLiveData(null)
    var errorMessage: MutableLiveData<String?> = MutableLiveData()

    fun signUpRequest(params: RegistrationParams) {
        viewModelScope.launch {
            try {
                response.value = auth.register(params)
            } catch (e: Throwable) {
                Log.e("ViewModelError", e.toString())
                errorMessage.value = isHttpException(e)
            }
        }
    }

}