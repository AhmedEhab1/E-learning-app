package com.raqamyat.ecommerceclub.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raqamyat.ecommerceclub.R
import com.raqamyat.ecommerceclub.databinding.AccountInfoFragmentBinding
import com.raqamyat.ecommerceclub.databinding.UpdatePasswordFragmentBinding


class UpdatePasswordFragment : Fragment() {
    private lateinit var binding: UpdatePasswordFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = UpdatePasswordFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun init() {
    }
}