package com.raqamyat.ecommerceclub.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import com.raqamyat.ecommerceclub.R
import com.raqamyat.ecommerceclub.databinding.SplashFragmentBinding
import com.raqamyat.ecommerceclub.ui.auth.UserData
import com.raqamyat.ecommerceclub.ui.profile.viewModels.AccountInfoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class SplashFragment : Fragment() {
    private lateinit var binding: SplashFragmentBinding
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SplashFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        errorMessage()
        appDirections()
    }

    private fun appDirections(){
        lifecycleScope.launch {
            var token = UserData(requireActivity()).getUserData()?.token
            if (token.equals("")|| token == null ){
                getData()
            }else {
                delay(2000L)
                findNavController(requireView()).navigate(
                    R.id.action_splashFragment_to_lessonsFragment
                )
            }

        }
    }

    private fun getData(){
        viewModel.getOnBoardingData();
        viewModel.response.observe(viewLifecycleOwner) {
            var bundle = Bundle()
            bundle.putSerializable("model", it?.data as java.io.Serializable)
            findNavController(requireView()).navigate(
                R.id.action_splashFragment_to_onboardingFragment, bundle
            )
        }
    }

    private fun errorMessage() {
        lifecycleScope.launch {
            viewModel.errorMessage.observe(viewLifecycleOwner) {
            }
        }
    }

}
