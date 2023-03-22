package com.raqamyat.ecommerceclub.ui.auth.signUp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.raqamyat.ecommerceclub.R
import com.raqamyat.ecommerceclub.base.BaseFragment
import com.raqamyat.ecommerceclub.databinding.SignUpFragmentBinding
import com.raqamyat.ecommerceclub.entities.RegistrationParams
import com.raqamyat.ecommerceclub.ui.auth.ShowPasswordHelper
import com.raqamyat.ecommerceclub.ui.auth.UserData
import com.raqamyat.ecommerceclub.utilities.FormValidation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignUpFragment : BaseFragment() {
    private lateinit var binding: SignUpFragmentBinding
    private var show = false
    private var show2 = false
    private val viewModel: SignUpViewModel by viewModels()
    private var formValidation = FormValidation()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = SignUpFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        showHidePassword()
        signUpResponse()
        errorMessage()
        isInputsValid()
    }

    private fun showHidePassword(){
        binding.view.setOnClickListener {
            ShowPasswordHelper().passwordVisibility(binding.view, binding.password, show)
            show = !show
        }
        binding.view2.setOnClickListener {
            ShowPasswordHelper().passwordVisibility(binding.view2, binding.confirmPassword, show2)
            show2 = !show2
        }
    }

    private fun isInputsValid() {
        binding.signUpBtn.setOnClickListener{
            val params = RegistrationParams()
            params.name = binding.name.text.toString()
            params.email = binding.email.text.toString()
            params.password = binding.password.text.toString()
            params.password_confirmation = binding.confirmPassword.text.toString()
            params.job = binding.job.text.toString()
            params.mobile = binding.phoneNumber.text.toString()
            checkParamsData(params)
        }
    }

    private fun checkParamsData(params: RegistrationParams){
        if (!formValidation.isValidEmail(params.email)){
            showErrorDialog(getString(R.string.enter_valid_email))
        }else if (!formValidation.isValidName(params.name.toString())){
            showErrorDialog(getString(R.string.enter_valid_name))
        }else if (!formValidation.isValidName(params.job.toString())){
            showErrorDialog(getString(R.string.enter_valid_job))
        }else if (!formValidation.isValidPassword(params.password.toString())){
            showErrorDialog(getString(R.string.enter_valid_password))
        }else if (!formValidation.isValidPassword(params.password_confirmation.toString())){
            showErrorDialog(getString(R.string.enter_valid_password))
        }else if (!formValidation.isValidNumber(params.mobile.toString())){
            showErrorDialog(getString(R.string.enter_valid_mobile))
        }else if (!binding.checkBox.isChecked){
            showErrorDialog(getString(R.string.checkbox_message))
        }else {
            showLoading()
            viewModel.signUpRequest(params)
        }
    }

    private fun signUpResponse(){
        viewModel.response.observe(viewLifecycleOwner){
            dismissLoading()
            UserData(requireActivity()).saveUserData(it!!.data)
            getPreferencesHelper().putString("firstTime", "true")
            Navigation.findNavController(requireView()).navigate(
                R.id.action_signUpFragment_to_homeFragment
            )
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