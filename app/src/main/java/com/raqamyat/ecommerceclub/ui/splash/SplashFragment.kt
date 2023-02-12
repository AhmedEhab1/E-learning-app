package com.raqamyat.ecommerceclub.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation.findNavController
import com.raqamyat.ecommerceclub.R
import com.raqamyat.ecommerceclub.databinding.SplashFragmentBinding
import com.raqamyat.ecommerceclub.ui.auth.UserData
import kotlinx.coroutines.*


class SplashFragment : Fragment() {
    private lateinit var binding: SplashFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SplashFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            delay(2000L)
            var token = UserData(requireActivity()).getUserData()?.token
            if (token.equals("")|| token == null ){
                findNavController(requireView()).navigate(
                    R.id.action_splashFragment_to_onboardingFragment
                )
            }else {
                findNavController(requireView()).navigate(
                    R.id.action_splashFragment_to_homeFragment
                )
            }

        }
    }


}
