package com.raqamyat.ecommerceclub.utilities

import java.util.regex.Matcher
import java.util.regex.Pattern

class FormValidation {
    fun isValidEmail(email: String?): Boolean {
        val EMAIL_PATTERN = ("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
        val pattern: Pattern = Pattern.compile(EMAIL_PATTERN)
        val matcher: Matcher = pattern.matcher(email)
        return matcher.matches()
    }

    fun isValidPassword(pass: String): Boolean {
        return pass.isNotEmpty() && pass.length >= 6
    }

    fun isValidNumber(num: String): Boolean {
        return num.isNotEmpty() && num.length == 11
    }

    fun isValidName(name: String): Boolean {
        return name.isNotEmpty() && name.length < 30
    }
}