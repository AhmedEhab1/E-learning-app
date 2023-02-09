package com.raqamyat.ecommerceclub.ui.auth.forgetPassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.raqamyat.ecommerceclub.R
import com.raqamyat.ecommerceclub.databinding.ForgetPasswordFragmentBinding
import com.raqamyat.ecommerceclub.databinding.OnBoardingTabThreeBinding


class ForgetPasswordFragment : Fragment() {
    private lateinit var binding: ForgetPasswordFragmentBinding

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
        binding.next.setOnClickListener{
            Navigation.findNavController(requireView()).navigate(
                R.id.action_forgetPasswordFragment_to_otpFragment
            )
        }
    }
}