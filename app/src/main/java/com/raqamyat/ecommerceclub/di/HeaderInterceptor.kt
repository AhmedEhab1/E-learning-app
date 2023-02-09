package com.raqamyat.ecommerceclub.di

import com.raqamyat.ecommerceclub.data.Constants
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "bearer "+(Constants.TOKEN))
                .addHeader("device_id", Constants.DEVICE_ID)
                .build()
        )
    }
}