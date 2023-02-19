package com.raqamyat.ecommerceclub.ui.profile.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raqamyat.ecommerceclub.entities.*
import com.raqamyat.ecommerceclub.repository.AuthorizationRepository
import com.raqamyat.ecommerceclub.repository.ProfileRepository
import com.raqamyat.ecommerceclub.utilities.JsonHelper.isHttpException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class AccountInfoViewModel @Inject constructor(
    private val auth: ProfileRepository
) : ViewModel() {
    val response: MutableLiveData<APIResponse<UserModel>?> = MutableLiveData()
    var errorMessage: MutableLiveData<String?> = MutableLiveData()

    fun updateProfile(params: AccountInfoParams) {
        viewModelScope.launch {
            try {
                response.value = auth.updateProfile(params)
            } catch (e: Throwable) {
                Log.e("ViewModelError", e.toString())
                errorMessage.value = isHttpException(e)
            }
        }
    }

}