package com.raqamyat.ecommerceclub.ui.lessons.tabs

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raqamyat.ecommerceclub.entities.*
import com.raqamyat.ecommerceclub.repository.HomeRepository
import com.raqamyat.ecommerceclub.utilities.JsonHelper.isHttpException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LessonsQuestionViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {
    val response: MutableLiveData<APIResponse<List<Question>>?> = MutableLiveData()
    var errorMessage: MutableLiveData<String?> = MutableLiveData()

    fun addQuestion(addQuestionRequest: AddQuestionRequest) {
        viewModelScope.launch {
            try {
                response.value = repository.addQuestion(addQuestionRequest)
            } catch (e: Throwable) {
                Log.e("ViewModelError", e.toString())
                errorMessage.value = isHttpException(e)
            }
        }
    }

}