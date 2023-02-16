package com.raqamyat.ecommerceclub.ui.auth.forgetPassword

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
import com.raqamyat.ecommerceclub.databinding.OnBoardingTabThreeBinding
import com.raqamyat.ecommerceclub.entities.ForgetPasswordModel
import com.raqamyat.ecommerceclub.ui.auth.UserData
import com.raqamyat.ecommerceclub.ui.auth.login.LoginViewModel
import com.raqamyat.ecommerceclub.utilities.FormValidation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ForgetPasswordFragment : BaseFragment() {
    private lateinit var binding: ForgetPasswordFragmentBinding
    private val viewModel: ForgetPasswordViewModel by viewModels()
    private var formValidation = FormValidation()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = ForgetPasswordFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        errorMessage()
        sendCode()
        requestResponse()
    }

    private fun errorMessage() {
        lifecycleScope.launch {
            viewModel.errorMessage.observe(viewLifecycleOwner) {
                showErrorDialog(it.toString())
            }
        }
    }

    private fun sendCode(){
        binding.next.setOnClickListener{
            var email = binding.email.text.toString()
            if (!formValidation.isValidEmail(email)){
                showErrorDialog(getString(R.string.enter_valid_email))
            }else{
                showLoading()
                viewModel.sendCode(ForgetPasswordModel(email = email))
            }
        }
    }

    private fun requestResponse() {
        lifecycleScope.launch {
            viewModel.response.observe(viewLifecycleOwner) {
                dismissLoading()
                var bundle = Bundle()
                bundle.putSerializable("model" , it?.data as java.io.Serializable)
                Navigation.findNavController(requireView()).navigate(
                    R.id.action_forgetPasswordFragment_to_otpFragment, bundle
                )
            }
        }
    }
}