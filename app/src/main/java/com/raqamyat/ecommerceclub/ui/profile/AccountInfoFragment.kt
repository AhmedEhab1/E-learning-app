package com.raqamyat.ecommerceclub.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.raqamyat.ecommerceclub.R
import com.raqamyat.ecommerceclub.base.BaseFragment
import com.raqamyat.ecommerceclub.databinding.AccountInfoFragmentBinding
import com.raqamyat.ecommerceclub.entities.AccountInfoParams
import com.raqamyat.ecommerceclub.ui.auth.UserData
import com.raqamyat.ecommerceclub.ui.profile.viewModels.AccountInfoViewModel
import com.raqamyat.ecommerceclub.utilities.FormValidation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AccountInfoFragment : BaseFragment() {
    private lateinit var binding: AccountInfoFragmentBinding
    private var formValidation = FormValidation()
    private val viewModel: AccountInfoViewModel by viewModels()
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
            binding = AccountInfoFragmentBinding.inflate(inflater, container, false)
        }
        return binding.root
    }

    private fun init() {
        isInputsValid()
        errorMessage()
        response()
        setData()
    }

    private fun isInputsValid() {
        binding.saveBtn.setOnClickListener {
            val params = AccountInfoParams()
            params.name = binding.name.text.toString()
            params.email = binding.email.text.toString()
            params.mobile = binding.phoneNumber.text.toString()
            checkParamsData(params)
        }
    }

    private fun checkParamsData(params: AccountInfoParams) {
        if (!formValidation.isValidEmail(params.email)) {
            showErrorDialog(getString(R.string.enter_valid_email))
        } else if (!formValidation.isValidName(params.name.toString())) {
            showErrorDialog(getString(R.string.enter_valid_name))
        } else if (!formValidation.isValidNumber(params.mobile.toString())) {
            showErrorDialog(getString(R.string.enter_valid_mobile))
        } else {
            showLoading()
            viewModel.updateProfile(params)
        }
    }

    private fun response() {
        viewModel.response.observe(viewLifecycleOwner) {
            dismissLoading()
            showErrorDialog(it?.message.toString())
            UserData(requireActivity()).saveUserData(it!!.data)
        }
    }

    private fun setData() {
        var userModel = UserData(requireActivity())
        binding.name.setText(userModel.getUserData()?.name.toString())
        binding.email.setText(userModel.getUserData()?.email.toString())
        binding.phoneNumber.setText(userModel.getUserData()?.mobile.toString())

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