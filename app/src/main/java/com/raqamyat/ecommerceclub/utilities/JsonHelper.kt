package com.raqamyat.ecommerceclub.utilities

import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException

object JsonHelper {
    fun isHttpException(error: Throwable): String {
        return if (error is HttpException) {
            try {
                val errorBody = error.response()!!.errorBody()!!.string()
                getErrorMessageDetails(errorBody)
            } catch (e: IOException) {
                e.printStackTrace()
                error.toString()
            }
        } else error.toString()
    }

    fun getErrorMessageDetails(errorMessage: String?): String {
        try {
            val jsonObject = JSONObject(errorMessage)
            return jsonObject.getString("message")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return "error"
    }
}