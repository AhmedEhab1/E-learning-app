package com.raqamyat.ecommerceclub.ui.auth.forgetPassword

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.raqamyat.ecommerceclub.R
import com.raqamyat.ecommerceclub.base.BaseFragment
import com.raqamyat.ecommerceclub.databinding.OtpFragmentBinding
import com.raqamyat.ecommerceclub.entities.ForgetPasswordModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class OtpFragment : BaseFragment() {
    private lateinit var binding: OtpFragmentBinding
    private lateinit var forgetPasswordModel: ForgetPasswordModel
    private val viewModel: ForgetPasswordViewModel by viewModels()

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
        forgetPasswordModel  = arguments?.getSerializable("model")as ForgetPasswordModel
        pinView()
        errorMessage()
        requestResponse()
    }

    private fun pinView(){
        binding.pinview.setPinViewEventListener { pinview, fromUser ->
            var data = pinview?.value.toString()
            if (data != forgetPasswordModel.verify_code){
                showErrorDialog(getString(R.string.wrong_code))
            }else{
                showLoading()
                viewModel.sendCode(forgetPasswordModel)
            }
        }
    }

    private fun errorMessage() {
        lifecycleScope.launch {
            viewModel.errorMessage.observe(viewLifecycleOwner) {
                showErrorDialog(it.toString())
            }
        }
    }

    private fun requestResponse() {
        lifecycleScope.launch {
            viewModel.response.observe(viewLifecycleOwner) {
                dismissLoading()
                lifecycleScope.launch() {
                    var bundle = Bundle()
                    bundle.putSerializable("model" , it?.data as java.io.Serializable)
                    showLottieImage(bundle)
                }
            }
        }
    }

    private suspend fun showLottieImage(bundle : Bundle){
        binding.lottie.visibility = View.VISIBLE
        binding.forgetPasswordInput.visibility = View.GONE
        delay(3200)
        Navigation.findNavController(requireView()).navigate(
            R.id.action_otpFragment_to_resetPasswordFragment, bundle
        )
    }
}