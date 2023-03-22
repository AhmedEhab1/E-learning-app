package com.raqamyat.ecommerceclub.ui.auth

import android.content.Context
import android.util.Log
import com.raqamyat.ecommerceclub.R
import com.raqamyat.ecommerceclub.data.Constants
import com.raqamyat.ecommerceclub.data.PreferencesHelper
import com.raqamyat.ecommerceclub.entities.UserModel

class UserData(private var context: Context? = null) {

    fun saveUserData(model: UserModel) {
        model.firstTime?.let {  getPreferencesHelper().putString("firstTime", "true")}
        model.id?.let { getPreferencesHelper().putInt("userId", it.toInt()) }
        model.name?.let { getPreferencesHelper().putString("name", it.toString()) }
        model.email?.let { getPreferencesHelper().putString("email", it.toString()) }
        model.image?.let { getPreferencesHelper().putString("image", it.toString()) }
        model.job?.let { getPreferencesHelper().putString("job", it.toString()) }
        model.mobile?.let { getPreferencesHelper().putString("mobile", it.toString()) }
        model.token?.let { getPreferencesHelper().putString("token", it.toString()) }
        model.is_verified?.let { getPreferencesHelper().putBoolean("is_verified", it) }
        Constants.TOKEN = getPreferencesHelper().getString("token").toString()
    }

    fun getUserData(): UserModel? {
        val userModel = UserModel()
        Constants.TOKEN = getPreferencesHelper().getString("token").toString()
        userModel.id = getPreferencesHelper().getInt("userId")
        userModel.email = getPreferencesHelper().getString("email")
        userModel.image = getPreferencesHelper().getString("image")
        userModel.name = getPreferencesHelper().getString("name")
        userModel.job = getPreferencesHelper().getString("job")
        userModel.mobile = getPreferencesHelper().getString("mobile")
        userModel.token = getPreferencesHelper().getString("token")
        userModel.is_verified = getPreferencesHelper().getBoolean("is_verified")
        userModel.firstTime = getPreferencesHelper().getString("firstTime")

        Log.d("token", "getUserData: " + Constants.TOKEN)
        return userModel
    }

    fun getPreferencesHelper(): PreferencesHelper {
        return PreferencesHelper(
            context!!.getSharedPreferences(
                context!!.getString(R.string.app_name), Context.MODE_PRIVATE
            )
        )
    }

    fun logout() {
        getPreferencesHelper().removeKey("token")
        getPreferencesHelper().removeKey("userId")
        getPreferencesHelper().removeKey("email")
        getPreferencesHelper().removeKey("image")
        getPreferencesHelper().removeKey("job")
        getPreferencesHelper().removeKey("mobile")
        getPreferencesHelper().removeKey("is_verified")
        Constants.TOKEN =""
    }


}