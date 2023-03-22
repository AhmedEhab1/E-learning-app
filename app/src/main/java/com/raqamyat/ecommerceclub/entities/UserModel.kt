package com.raqamyat.ecommerceclub.entities

data class UserModel(
    var certificates: List<Any>? = null,
    var company: Any? = null,
    var email: String? = null,
    var id: Int? = null,
    var image: String? = null,
    var is_verified: Boolean? = null,
    var job: String? = null,
    var mobile: String? = null,
    var name: String? = null,
    var token: String? = null,
    var verify_code: String? = null,
    var firstTime : String? = null
)
