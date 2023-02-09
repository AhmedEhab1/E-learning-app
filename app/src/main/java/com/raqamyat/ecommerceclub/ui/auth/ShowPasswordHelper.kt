package com.raqamyat.ecommerceclub.ui.auth

import android.text.InputType
import android.widget.EditText
import android.widget.ImageView
import com.raqamyat.ecommerceclub.R

class ShowPasswordHelper {

     fun passwordVisibility(imageView: ImageView, password: EditText, isPasswordToggelShow: Boolean ) {
        if (isPasswordToggelShow) {
            imageView.setImageResource(R.drawable.hide_password_icon)
            hidePassword(password.length(), password)
        } else {
            imageView.setImageResource(R.drawable.show_password_icon)
            showPassword(password)
            password.setSelection(password.length())
        }
    }

     fun hidePassword(s: Int , password : EditText) {
        password.inputType = InputType.TYPE_CLASS_TEXT or
                InputType.TYPE_TEXT_VARIATION_PASSWORD or InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
        password.setSelection(s)
    }

     fun showPassword(password : EditText) {
        password.inputType = InputType.TYPE_CLASS_TEXT or
                InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
    }
}