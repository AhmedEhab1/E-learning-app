package com.raqamyat.ecommerceclub.entities

data class RegistrationParams(
    var email: String? = null,
    var name: String? = null,
    var job: String? = null,
    var mobile: String? = null,
    var password :String? = null,
    var password_confirmation :String? = null,
    var accept_terms :String? = null
)
