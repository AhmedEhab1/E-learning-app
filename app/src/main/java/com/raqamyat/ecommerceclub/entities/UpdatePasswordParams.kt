package com.raqamyat.ecommerceclub.entities

data class UpdatePasswordParams(
    var current_password : String? = null,
    var password : String? = null,
    var password_confirmation : String? = null,
)
