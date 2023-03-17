package com.raqamyat.ecommerceclub.repository

import com.raqamyat.ecommerceclub.entities.*
import com.raqamyat.ecommerceclub.network.ApiService
import okhttp3.MultipartBody
import javax.inject.Inject

class HomeRepository@Inject constructor (private val  apiService: ApiService) {

   suspend fun getHome(): APIResponse<HomeModel> {
       return apiService.getHome()
    }
    suspend fun getLessons(): APIResponse<List<LastEpisode>> {
       return apiService.getLessons()
    }

    suspend fun updateLesson(request: UpdateLessonParams): APIResponse<LastEpisode> {
        return apiService.updateLesson(request)
    }

}