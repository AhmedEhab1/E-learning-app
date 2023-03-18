package com.raqamyat.ecommerceclub.entities

data class ExamResponse(
     val is_success : Boolean? = null,
     val exam : List<ExamModel>? = null,
     val try_numbers : Int? = null,
)
