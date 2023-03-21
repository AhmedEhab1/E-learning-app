package com.raqamyat.ecommerceclub.entities

import kotlinx.serialization.Serializable

@Serializable
data class ExamAnswersResponse(
    var tryNumber : Int? = null,
    var degree : Int? = null,
    var isPass : Boolean? = null,
    var correctAnswers : Int? = null,
    var wrongAnswers : Int? = null,
    var message : String? = null,
):java.io.Serializable
