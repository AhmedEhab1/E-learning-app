package com.raqamyat.ecommerceclub.ui.onBoarding.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.raqamyat.ecommerceclub.databinding.OnBoardingTabThreeBinding
import com.raqamyat.ecommerceclub.ui.onBoarding.fragments.OnBoardingTabOneFragment
import com.raqamyat.ecommerceclub.ui.onBoarding.fragments.OnBoardingTabThree
import com.raqamyat.ecommerceclub.ui.onBoarding.fragments.OnBoardingTabTwoFragment

class OnBoardingPagerAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getCount(): Int {
            return 3;
        }

        override fun getItem(position: Int): Fragment {
            when(position) {
                0 -> {
                    return OnBoardingTabOneFragment()
                }
                1 -> {
                    return OnBoardingTabTwoFragment()
                } 2 -> {
                    return OnBoardingTabThree()
                }
                else -> {
                    return OnBoardingTabOneFragment()
                }
            }
        }

        override fun getPageTitle(position: Int): CharSequence? {
            when(position) {
                0 -> {
                    return "Tab 1"
                }
                1 -> {
                    return "Tab 2"
                }
                2 -> {
                    return "Tab 3"
                }
            }
            return super.getPageTitle(position)
        }

    }