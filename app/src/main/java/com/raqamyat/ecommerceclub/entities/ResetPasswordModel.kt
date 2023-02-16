package com.raqamyat.ecommerceclub.entities

import kotlinx.serialization.Serializable

data class ResetPasswordModel(
    var email: String? = null,
    var password: String? = null,
    var password_confirmation: String? = null,
    var verify_code: String? = null
)
