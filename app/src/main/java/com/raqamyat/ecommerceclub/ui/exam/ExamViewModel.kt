package com.raqamyat.ecommerceclub.ui.exam

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
class ExamViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {
    val response: MutableLiveData<APIResponse<ExamResponse>?> = MutableLiveData()
    val examAnswersResponse: MutableLiveData<APIResponse<ExamAnswersResponse>?> = MutableLiveData()
    var errorMessage: MutableLiveData<String?> = MutableLiveData()

    fun getExam() {
        viewModelScope.launch {
            try {
                response.value = repository.getExam()
            } catch (e: Throwable) {
                Log.e("ViewModelError", e.toString())
                errorMessage.value = isHttpException(e)
            }
        }
    }

    fun examAnswers(request: List<AnswersRequest>) {
        viewModelScope.launch {
            try {
                examAnswersResponse.value = repository.examAnswers(request)
            } catch (e: Throwable) {
                Log.e("ViewModelError", e.toString())
                errorMessage.value = isHttpException(e)
            }
        }
    }

}