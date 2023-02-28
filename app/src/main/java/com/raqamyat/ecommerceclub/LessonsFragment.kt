package com.raqamyat.ecommerceclub

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raqamyat.ecommerceclub.base.BaseFragment
import com.raqamyat.ecommerceclub.databinding.HomeFragmentBinding
import com.raqamyat.ecommerceclub.databinding.LessonsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LessonsFragment : BaseFragment() {
    private lateinit var binding: LessonsFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = LessonsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun init() {

    }

}