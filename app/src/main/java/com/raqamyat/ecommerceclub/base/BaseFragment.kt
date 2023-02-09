package com.raqamyat.ecommerceclub.base

import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.HapticFeedbackConstants
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.raqamyat.ecommerceclub.utilities.Loading
import com.raqamyat.ecommerceclub.utilities.errorDialog.ErrorDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

open class BaseFragment : Fragment() {
    private val loading = Loading()

    fun showLoading() {
        loading.show(requireActivity().supportFragmentManager, "loading")
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


}