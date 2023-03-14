package com.raqamyat.ecommerceclub.ui.lessons.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raqamyat.ecommerceclub.R
import com.raqamyat.ecommerceclub.databinding.LessonsQuestionsTabBinding
import com.raqamyat.ecommerceclub.databinding.LessonsTabFragmentBinding
import com.raqamyat.ecommerceclub.entities.LastEpisode
import com.raqamyat.ecommerceclub.entities.Question

class LessonsQuestionsTab(private val model: List<Question>) : Fragment() {
    private lateinit var binding : LessonsQuestionsTabBinding;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = LessonsQuestionsTabBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){

    }
}