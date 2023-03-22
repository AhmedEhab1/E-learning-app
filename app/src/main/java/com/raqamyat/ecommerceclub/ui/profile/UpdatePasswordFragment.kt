package com.raqamyat.ecommerceclub.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.raqamyat.ecommerceclub.R
import com.raqamyat.ecommerceclub.base.BaseFragment
import com.raqamyat.ecommerceclub.databinding.AccountInfoFragmentBinding
import com.raqamyat.ecommerceclub.databinding.UpdatePasswordFragmentBinding
import com.raqamyat.ecommerceclub.entities.AccountInfoParams
import com.raqamyat.ecommerceclub.entities.UpdatePasswordParams
import com.raqamyat.ecommerceclub.ui.auth.UserData
import com.raqamyat.ecommerceclub.ui.profile.viewModels.AccountInfoViewModel
import com.raqamyat.ecommerceclub.ui.profile.viewModels.UpdatePasswordViewModel
import com.raqamyat.ecommerceclub.utilities.FormValidation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class UpdatePasswordFragment : BaseFragment() {
    private lateinit var binding: UpdatePasswordFragmentBinding
    private var formValidation = FormValidation()
    private val viewModel: UpdatePasswordViewModel by viewModels()
    private var shouldRefreshOnResume = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!shouldRefreshOnResume) {
            init()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        if (!shouldRefreshOnResume) {
            binding = UpdatePasswordFragmentBinding.inflate(inflater, container, false)
        }
        return binding.root
    }

    private fun init() {
        isInputsValid()
        response()
        errorMessage()
    }

    private fun isInputsValid() {
        binding.saveBtn.setOnClickListener {
            val params = UpdatePasswordParams()
            params.current_password = binding.oldPassword.text.toString()
            params.password = binding.newPassword.text.toString()
            params.password_confirmation = binding.confirmPassword.text.toString()
            checkParamsData(params)
        }
    }

    private fun checkParamsData(params: UpdatePasswordParams) {
        if (!formValidation.isValidPassword(params.current_password.toString())) {
            showErrorDialog(getString(R.string.enter_valid_password))
        } else if (!formValidation.isValidPassword(params.password.toString())) {
            showErrorDialog(getString(R.string.enter_valid_password))
        } else if (!formValidation.isValidPassword(params.password_confirmation.toString())) {
            showErrorDialog(getString(R.string.enter_valid_password))
        } else if (params.password != params.password_confirmation){
            showErrorDialog(getString(R.string.enter_same_password))
        } else {
            showLoading()
            viewModel.updatePassword(params)
        }
    }


    private fun response() {
        viewModel.response.observe(viewLifecycleOwner) {
            dismissLoading()
            showErrorDialog(it?.message.toString())
            UserData(requireActivity()).saveUserData(it!!.data)
        }
    }

    private fun errorMessage() {
        lifecycleScope.launch {
            viewModel.errorMessage.observe(viewLifecycleOwner) {
                showErrorDialog(it.toString())
            }
        }
    }

    override fun onPause() {
        super.onPause()
        shouldRefreshOnResume = true
    }
}