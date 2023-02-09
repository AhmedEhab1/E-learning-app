package com.raqamyat.ecommerceclub.ui.onBoarding.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raqamyat.ecommerceclub.R
import com.raqamyat.ecommerceclub.databinding.FragmentOnBoardingTabTwoBinding
import com.raqamyat.ecommerceclub.databinding.OnBoardingTabOneBinding


class OnBoardingTabTwoFragment : Fragment() {
    private lateinit var binding : FragmentOnBoardingTabTwoBinding ;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentOnBoardingTabTwoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){

    }
}