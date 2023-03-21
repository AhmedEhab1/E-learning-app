package com.raqamyat.ecommerceclub

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.raqamyat.ecommerceclub.databinding.ExamResultFailBinding
import com.raqamyat.ecommerceclub.databinding.OnBoardingTabThreeBinding
import com.raqamyat.ecommerceclub.entities.ExamAnswersResponse
import com.raqamyat.ecommerceclub.entities.WelcomeModel


class ExamResultFailFragment : Fragment() {
    private lateinit var binding: ExamResultFailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = ExamResultFailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        val model: ExamAnswersResponse = arguments?.getSerializable("model") as ExamAnswersResponse
        val degree = model.degree.toString() + " %"
        binding.degree.text = degree
        binding.correctAnswers.text = model.correctAnswers.toString()
        binding.wrongAnswers.text = model.wrongAnswers.toString()
        val tryNumber ="يتبقى أمامك "+ model.tryNumber+" محاولات لاعادة الاختبار"
        binding.tryNum.text = tryNumber
        btnListeners()
    }

    private fun btnListeners(){
        binding.back.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(
                R.id.action_examResultFailFragment_to_lessonsFragment
            )
        }
        binding.askForMoreTry.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(
                R.id.action_examResultFailFragment_to_examInstructions
            )
        }
    }
}