package com.raqamyat.ecommerceclub.entities

data class Question(
    val answer: String,
    val id: Int,
    val question: String,
    val user_name: String,
    val user_image: String,
    val date: String,
    val answer_date: String,
)