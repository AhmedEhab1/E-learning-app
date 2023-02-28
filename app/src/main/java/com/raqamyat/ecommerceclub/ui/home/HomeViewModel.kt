package com.raqamyat.ecommerceclub.ui.home

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
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {
    val response: MutableLiveData<APIResponse<HomeModel>?> = MutableLiveData()
    var errorMessage: MutableLiveData<String?> = MutableLiveData()

    fun getHome() {
        viewModelScope.launch {
            try {
                response.value = repository.getHome()
            } catch (e: Throwable) {
                Log.e("ViewModelError", e.toString())
                errorMessage.value = isHttpException(e)
            }
        }
    }

}