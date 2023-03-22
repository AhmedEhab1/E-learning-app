package com.raqamyat.ecommerceclub.ui.splash

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raqamyat.ecommerceclub.entities.*
import com.raqamyat.ecommerceclub.repository.AuthorizationRepository
import com.raqamyat.ecommerceclub.repository.HomeRepository
import com.raqamyat.ecommerceclub.repository.ProfileRepository
import com.raqamyat.ecommerceclub.utilities.JsonHelper.isHttpException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val auth: AuthorizationRepository
) : ViewModel() {
    val response: MutableLiveData<APIResponse<List<WelcomeModel>>?> = MutableLiveData()
    var errorMessage: MutableLiveData<String?> = MutableLiveData()
    val homeResponse: MutableLiveData<APIResponse<HomeModel>?> = MutableLiveData()

    fun getOnBoardingData() {
        viewModelScope.launch {
            try {
                response.value = auth.getOnBoardingData()
            } catch (e: Throwable) {
                Log.e("ViewModelError", e.toString())
                errorMessage.value = isHttpException(e)
            }
        }
    }

    fun getHome() {
        viewModelScope.launch {
            try {
                homeResponse.value = auth.getHome()
            } catch (e: Throwable) {
                Log.e("ViewModelError", e.toString())
                errorMessage.value = isHttpException(e)
            }
        }
    }

}