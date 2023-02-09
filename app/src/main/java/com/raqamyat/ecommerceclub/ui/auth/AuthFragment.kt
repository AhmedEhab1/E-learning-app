package com.raqamyat.ecommerceclub.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.raqamyat.ecommerceclub.R
import com.raqamyat.ecommerceclub.databinding.FragmentAuthBinding


class AuthFragment : Fragment() {
    private lateinit var binding: FragmentAuthBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        binding.loginBtn.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(
                R.id.action_authFragment_to_loginFragment
            )
        }
        binding.createAccount.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(
                R.id.action_authFragment_to_signUpFragment
            )
        }
    }
}