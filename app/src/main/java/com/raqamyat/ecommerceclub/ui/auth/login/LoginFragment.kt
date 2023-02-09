package com.raqamyat.ecommerceclub.ui.auth.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.raqamyat.ecommerceclub.R
import com.raqamyat.ecommerceclub.base.BaseFragment
import com.raqamyat.ecommerceclub.databinding.LoginFragmentBinding
import com.raqamyat.ecommerceclub.entities.LoginParams
import com.raqamyat.ecommerceclub.ui.auth.ShowPasswordHelper
import com.raqamyat.ecommerceclub.utilities.FormValidation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LoginFragment : BaseFragment()  {
    private lateinit var binding: LoginFragmentBinding
    private var show = false
    private val viewModel: LoginViewModel by viewModels()
    private var formValidation = FormValidation()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        onViewClicked()
        isInputsValid()
        loginResponse()
        errorMessage()
    }

    private fun onViewClicked() {
        showHidePassword()
        binding.createAccount.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(
                R.id.action_loginFragment_to_signUpFragment
            )
        }
        binding.forgetPassword.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(
                R.id.action_loginFragment_to_forgetPasswordFragment
            )
        }
    }

    private fun showHidePassword() {
        binding.view.setOnClickListener {
            ShowPasswordHelper().passwordVisibility(binding.view, binding.password, show)
            show = !show
        }
    }

    private fun isInputsValid() {
        binding.loginBtn.setOnClickListener{
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
            if (!formValidation.isValidEmail(email)){
                showErrorDialog(getString(R.string.enter_valid_email))
            }else if (!formValidation.isValidPassword(password)){
                showErrorDialog(getString(R.string.enter_valid_password))
            }else {
                val request = LoginParams()
                request.email = email
                request.password = password
                login(request)
            }
        }
    }

    private fun login(request : LoginParams) {
        showLoading()
        viewModel.loginRequest(request)
    }

    private fun loginResponse() {
        lifecycleScope.launch {
            viewModel.loginResponse.observe(viewLifecycleOwner) {
                dismissLoading()
                Log.d("TAG", "loginResponse: " + it?.data?.email)
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
}