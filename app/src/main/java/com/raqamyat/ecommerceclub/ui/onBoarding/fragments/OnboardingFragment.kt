package com.raqamyat.ecommerceclub.ui.onBoarding.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.raqamyat.ecommerceclub.R
import com.raqamyat.ecommerceclub.databinding.OnboardingFragmentBinding
import com.raqamyat.ecommerceclub.ui.onBoarding.adapter.OnBoardingPagerAdapter

class OnboardingFragment : Fragment() {
    private lateinit var binding : OnboardingFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = OnboardingFragmentBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        binding.viewPager.adapter = OnBoardingPagerAdapter(requireActivity().supportFragmentManager)

        binding.button.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(
                R.id.action_onboardingFragment_to_authFragment
            )
        }
    }

}