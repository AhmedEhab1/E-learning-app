package com.raqamyat.ecommerceclub.ui.lessons.tabs

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.raqamyat.ecommerceclub.R
import com.raqamyat.ecommerceclub.databinding.LessonsQuestionsTabBinding
import com.raqamyat.ecommerceclub.databinding.LessonsTabFragmentBinding
import com.raqamyat.ecommerceclub.entities.LastEpisode
import com.raqamyat.ecommerceclub.entities.Question
import com.raqamyat.ecommerceclub.ui.lessons.LessonsAdapter
import com.raqamyat.ecommerceclub.ui.lessons.QuestionsDialog
import com.raqamyat.ecommerceclub.ui.lessons.adapters.LessonQuestionsAdapter
import com.raqamyat.ecommerceclub.utilities.errorDialog.ErrorDialog

class LessonsQuestionsTab(private val model: List<Question>) : Fragment(),
    QuestionsDialog.QuestionsListener {
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
        initAdapter(model)
        binding.ask.setOnClickListener{openAskDialog()}
    }

    private fun initAdapter(model: List<Question>) {
        if (model.isNotEmpty()){
            binding.noData.visibility = View.GONE
        }
        val adapter = LessonQuestionsAdapter(model, requireActivity())
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager =LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL , false)
    }

    override fun onAddQuestionClicked(position: Int) {
        TODO("Not yet implemented")
    }

    private fun openAskDialog(){
        val bundle = Bundle()
        bundle.putString("message", "message")
        QuestionsDialog().newInstance(this, bundle)
            .show(requireActivity().supportFragmentManager, "loading")
    }
}