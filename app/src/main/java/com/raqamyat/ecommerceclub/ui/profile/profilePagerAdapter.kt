package com.raqamyat.ecommerceclub.ui.profile

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.raqamyat.ecommerceclub.databinding.OnBoardingTabThreeBinding
import com.raqamyat.ecommerceclub.ui.onBoarding.fragments.OnBoardingTabOneFragment
import com.raqamyat.ecommerceclub.ui.onBoarding.fragments.OnBoardingTabThree
import com.raqamyat.ecommerceclub.ui.onBoarding.fragments.OnBoardingTabTwoFragment

class profilePagerAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getCount(): Int {
            return 3;
        }

        override fun getItem(position: Int): Fragment {
            when(position) {
                0 -> {
                    return AccountInfoFragment()
                }
                1 -> {
                    return UpdatePasswordFragment()
                } 2 -> {
                    return CertificateFragment()
                }
                else -> {
                    return AccountInfoFragment()
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