package com.raqamyat.ecommerceclub.repository

import com.raqamyat.ecommerceclub.entities.*
import com.raqamyat.ecommerceclub.network.ApiService
import okhttp3.MultipartBody
import javax.inject.Inject

class HomeRepository@Inject constructor (private val  apiService: ApiService) {

   suspend fun getHome(): APIResponse<HomeModel> {
       return apiService.getHome()
    }

   suspend fun getExam(): APIResponse<ExamResponse> {
       return apiService.getExam()
    }
    suspend fun getLessons(): APIResponse<List<LastEpisode>> {
       return apiService.getLessons()
    }

    suspend fun updateLesson(request: UpdateLessonParams): APIResponse<LastEpisode> {
        return apiService.updateLesson(request)
    }

    suspend fun addQuestion(request: AddQuestionRequest): APIResponse<Any> {
        return apiService.addQuestion(request)
    }

    suspend fun examAnswers(request: List<AnswersRequest>): APIResponse<ExamAnswersResponse> {
        return apiService.examAnswers(request)
    }

}