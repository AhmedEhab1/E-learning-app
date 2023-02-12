package com.raqamyat.ecommerceclub.data

import android.content.SharedPreferences

class PreferencesHelper(private var preferences: SharedPreferences?) {

    fun putString(key: String, value: String) {
        preferences!!.edit().putString(key, value).apply()
    }

    fun getString(key: String): String? {
        return preferences!!.getString(key, "")
    }

    fun putBoolean(key: String, value: Boolean) {
        preferences!!.edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String): Boolean {
        return preferences!!.getBoolean(key, false)
    }

    fun putInt(key: String, value: Int) {
        preferences!!.edit().putInt(key, value).apply()
    }

    fun getInt(key: String): Int {
        return preferences!!.getInt(key, -1)
    }

    fun clear() {
        preferences!!.edit().clear().apply()
    }

    fun removeKey(key: String) {
        preferences!!.edit().remove(key).apply()
    }

    fun containKey(key: String?): Boolean {
        return preferences!!.contains(key)
    }

    fun putFloat(key: String, value: Float) {
        preferences!!.edit().putFloat(key, value).apply()
    }

    fun getFloat(key: String): Float {
        return preferences!!.getFloat(key, 0f)
    }
}