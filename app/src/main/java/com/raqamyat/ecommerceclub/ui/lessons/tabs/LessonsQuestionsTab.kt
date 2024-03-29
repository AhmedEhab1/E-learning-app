package com.raqamyat.ecommerceclub.ui.lessons.tabs

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.raqamyat.ecommerceclub.R
import com.raqamyat.ecommerceclub.base.BaseFragment
import com.raqamyat.ecommerceclub.databinding.LessonsQuestionsTabBinding
import com.raqamyat.ecommerceclub.databinding.LessonsTabFragmentBinding
import com.raqamyat.ecommerceclub.entities.AddQuestionRequest
import com.raqamyat.ecommerceclub.entities.LastEpisode
import com.raqamyat.ecommerceclub.entities.Question
import com.raqamyat.ecommerceclub.ui.lessons.LessonsAdapter
import com.raqamyat.ecommerceclub.ui.lessons.LessonsViewModel
import com.raqamyat.ecommerceclub.ui.lessons.QuestionsDialog
import com.raqamyat.ecommerceclub.ui.lessons.adapters.LessonQuestionsAdapter
import com.raqamyat.ecommerceclub.utilities.errorDialog.ErrorDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LessonsQuestionsTab(private val model: LastEpisode) : BaseFragment(),
    QuestionsDialog.QuestionsListener {
    private lateinit var binding: LessonsQuestionsTabBinding
    private val viewModel: LessonsQuestionViewModel by viewModels()
    private var shouldRefreshOnResume = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        if (!shouldRefreshOnResume) {
            binding = LessonsQuestionsTabBinding.inflate(inflater, container, false)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!shouldRefreshOnResume) {
            init()
        }
    }

    private fun init() {
        initAdapter(model.questions!!)
        requestResponse()
        errorMessage()
        binding.ask.setOnClickListener { openAskDialog() }
    }

    private fun initAdapter(model: List<Question>) {
        if (model.isNotEmpty()) {
            binding.noData.visibility = View.GONE
        }
        val adapter = LessonQuestionsAdapter(model, requireActivity())
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
    }

    override fun onAddQuestionClicked(question: String) {
        Log.d("x", "onAddQuestionClicked: $question")
        showLoading()
        viewModel.addQuestion(AddQuestionRequest(question, model.id!!))

    }

    private fun requestResponse() {
        lifecycleScope.launch {
            viewModel.response.observe(viewLifecycleOwner) {
                lifecycleScope.launch {
                    dismissLoading()
                    showSnackBar("تم اضافة السؤال بنجاح")
                    var question: List<Question> = it?.data!!
                    initAdapter(question)

                }
            }
        }
    }


    private fun errorMessage() {
        lifecycleScope.launch {
            viewModel.errorMessage.observe(viewLifecycleOwner) {
                showErrorDialog(it.toString())
            }
        }
    }

    private fun openAskDialog() {
        val bundle = Bundle()
        bundle.putString("message", "message")
        QuestionsDialog(this)
            .show(requireActivity().supportFragmentManager, "loading")
    }

    override fun onPause() {
        super.onPause()
        shouldRefreshOnResume = true
    }
}