package com.raqamyat.ecommerceclub.entities

import kotlinx.serialization.Serializable

@Serializable
data class Question(
    val answer: String? = null,
    val id: Int? = null,
    val question: String? = null,
    val user_name: String? = null,
    val user_image: String? = null,
    val date: String? = null,
    val answer_date: String? = null ,
):java.io.Serializable