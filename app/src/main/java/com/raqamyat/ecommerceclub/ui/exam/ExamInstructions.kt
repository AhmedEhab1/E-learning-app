package com.raqamyat.ecommerceclub.ui.exam

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.raqamyat.ecommerceclub.R
import com.raqamyat.ecommerceclub.databinding.ExamInstructionsFragmentBinding
import com.raqamyat.ecommerceclub.databinding.OnBoardingTabThreeBinding

class ExamInstructions : Fragment() {
    private lateinit var binding: ExamInstructionsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = ExamInstructionsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        binding.startExam.setOnClickListener{
            Navigation.findNavController(requireView()).navigate(
                R.id.action_examInstructions_to_examFragment
            )
        }
    }
}