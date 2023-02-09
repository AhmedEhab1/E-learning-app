package com.raqamyat.ecommerceclub.ui.auth.forgetPassword

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.goodiebag.pinview.Pinview

import com.raqamyat.ecommerceclub.databinding.OtpFragmentBinding


class OtpFragment : Fragment() {
    private lateinit var binding: OtpFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = OtpFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        pinView()
    }

    private fun pinView(){
        binding.pinview.setPinViewEventListener { pinview, fromUser ->
            var data = pinview?.value
            Log.d("ehab", "onDataEntered: $data")
        }
    }
}