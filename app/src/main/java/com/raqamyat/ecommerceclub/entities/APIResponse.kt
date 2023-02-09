package com.raqamyat.ecommerceclub.entities

import com.google.gson.annotations.SerializedName

data class APIResponse<T>(
    @SerializedName("success") var status: String,
    @SerializedName("message") var message: String,
    @SerializedName("data") var data: T
)
