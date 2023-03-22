package com.raqamyat.ecommerceclub.base

import android.R
import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.raqamyat.ecommerceclub.data.PreferencesHelper
import com.raqamyat.ecommerceclub.utilities.Loading
import com.raqamyat.ecommerceclub.utilities.errorDialog.ErrorDialog


open class BaseFragment : Fragment() {
    private val loading = Loading()

    fun showLoading() {
        try {
            loading.show(requireActivity().supportFragmentManager, "loading")

        }catch (e : Exception){
            Log.e("error", "showLoading: ",e )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun dismissLoading() {
        if (loading.dialog != null)
            loading.dismiss()
    }

    fun showErrorDialog(message: String) {
        vibrate()
        dismissLoading()
        val bundle = Bundle()
        Log.e("error", "errorDialogMessage: $message")
        bundle.putString("message", message)
        ErrorDialog().newInstance(null, bundle)
            .show(requireActivity().supportFragmentManager, "loading")
    }

    private fun vibrate() {
        // requireView().performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
        val vibrator = getSystemService(requireContext(), Vibrator::class.java)
        vibrator?.let {
            if (Build.VERSION.SDK_INT >= 26) {
                it.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                @Suppress("DEPRECATION")
                it.vibrate(100)
            }
        }
    }

    public fun back() {
        findNavController(requireView()).popBackStack()
    }

    fun getPreferencesHelper(): PreferencesHelper {
        return PreferencesHelper(
            requireActivity()!!.getSharedPreferences(
                requireActivity()!!.getString(com.raqamyat.ecommerceclub.R.string.app_name),
                Context.MODE_PRIVATE
            )
        )
    }

    open fun showSnackBar(message: String?) {
        Snackbar.make(
            (requireActivity() as Activity).findViewById(R.id.content),
            message!!, Snackbar.LENGTH_LONG
        )
            .setAction("CLOSE") { }
            .setActionTextColor(requireActivity().getResources().getColor(R.color.holo_red_light))
            .setTextColor(requireActivity().getResources().getColor(R.color.white))
            .show()
        vibrate()
    }
}