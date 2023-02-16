package com.raqamyat.ecommerceclub.entities

import kotlinx.serialization.Serializable

@Serializable
data class ForgetPasswordModel(
    var email: String? = null,
    var verify_code: String? = null
):java.io.Serializable
