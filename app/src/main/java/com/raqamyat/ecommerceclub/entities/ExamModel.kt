package com.raqamyat.ecommerceclub.entities

data class ExamModel(
    val id :Int?= null,
    val question :String?= null,
    val answers :List<AnswersModel>?= null
)
