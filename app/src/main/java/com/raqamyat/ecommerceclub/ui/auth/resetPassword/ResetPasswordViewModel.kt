package com.raqamyat.ecommerceclub.ui.auth.resetPassword

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raqamyat.ecommerceclub.entities.APIResponse
import com.raqamyat.ecommerceclub.entities.ForgetPasswordModel
import com.raqamyat.ecommerceclub.entities.ResetPasswordModel
import com.raqamyat.ecommerceclub.repository.AuthorizationRepository
import com.raqamyat.ecommerceclub.utilities.JsonHelper.isHttpException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
    private val auth: AuthorizationRepository
) : ViewModel() {
    val response: MutableLiveData<APIResponse<Any>?> = MutableLiveData()
    var errorMessage: MutableLiveData<String?> = MutableLiveData()

    fun resetPassword(params: ResetPasswordModel) {
        viewModelScope.launch {
            try {
                response.value = auth.resetPassword(params)
            } catch (e: Throwable) {
                Log.e("ViewModelError", e.toString())
                errorMessage.value = isHttpException(e)
            }
        }
    }

}