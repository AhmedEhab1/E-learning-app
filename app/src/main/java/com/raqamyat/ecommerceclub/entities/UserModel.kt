package com.raqamyat.ecommerceclub.entities

data class UserModel(
    val certificates: List<Any>? = null,
    val company: Any? = null,
    val email: String? = null,
    val id: Int? = null,
    val image: String? = null,
    val is_verified: Boolean? = null,
    val job: String? = null,
    val mobile: String? = null,
    val name: String? = null,
    val token: String? = null,
    val verify_code: String? = null)
