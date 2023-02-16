package com.raqamyat.ecommerceclub.ui.auth.resetPassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.raqamyat.ecommerceclub.R
import com.raqamyat.ecommerceclub.base.BaseFragment
import com.raqamyat.ecommerceclub.databinding.ForgetPasswordFragmentBinding
import com.raqamyat.ecommerceclub.databinding.ResetPasswordFragmentBinding
import com.raqamyat.ecommerceclub.entities.ForgetPasswordModel
import com.raqamyat.ecommerceclub.entities.ResetPasswordModel
import com.raqamyat.ecommerceclub.ui.auth.forgetPassword.ForgetPasswordViewModel
import com.raqamyat.ecommerceclub.utilities.FormValidation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import okhttp3.Interceptor

@AndroidEntryPoint
class ResetPasswordFragment : BaseFragment() {
    private lateinit var binding: ResetPasswordFragmentBinding
    private lateinit var forgetPasswordModel: ForgetPasswordModel
    private val viewModel: ResetPasswordViewModel by viewModels()
    private var formValidation = FormValidation()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = ResetPasswordFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        forgetPasswordModel  = arguments?.getSerializable("model")as ForgetPasswordModel
        errorMessage()
        requestResponse()
        binding.login.setOnClickListener{resetPassword()}
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
                Navigation.findNavController(requireView()).navigate(
                    R.id.action_resetPasswordFragment_to_loginFragment
                )
            }
        }
    }

    private fun resetPassword(){
        var password = binding.password.text.toString()
        var confirmPassword = binding.confirmPassword.text.toString()
        if (!formValidation.isValidPassword(password)||!formValidation.isValidPassword(confirmPassword)||
            password != confirmPassword){
            showErrorDialog(getString(R.string.enter_valid_password))
        }else{
            showLoading()
            viewModel.resetPassword(ResetPasswordModel(email = forgetPasswordModel.email , verify_code =
            forgetPasswordModel.verify_code, password =password   , password_confirmation = confirmPassword ))
        }
    }
}