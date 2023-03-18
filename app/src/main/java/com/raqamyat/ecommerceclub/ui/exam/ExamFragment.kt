package com.raqamyat.ecommerceclub.ui.exam

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.raqamyat.ecommerceclub.base.BaseFragment
import com.raqamyat.ecommerceclub.databinding.ExamFragmentBinding
import com.raqamyat.ecommerceclub.entities.ExamResponse
import com.raqamyat.ecommerceclub.ui.exam.adapter.ExamAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import kotlin.math.roundToInt


@AndroidEntryPoint
class ExamFragment : BaseFragment(), ExamAdapter.AnswersListener {
    private lateinit var binding: ExamFragmentBinding
    private val viewModel: ExamViewModel by viewModels()
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var position = 0
    private lateinit var examResponse: ExamResponse

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = ExamFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        getExam()
        errorMessage()
        requestResponse()
        changeQuestion()
        timer()
    }

    private fun getExam() {
        showLoading()
        viewModel.getExam()
    }

    private fun errorMessage() {
        lifecycleScope.launch {
            viewModel.errorMessage.observe(viewLifecycleOwner) {
                showErrorDialog(it.toString())
            }
        }
    }

    private fun requestResponse() {
        lifecycleScope.launch {
            viewModel.response.observe(viewLifecycleOwner) {
                dismissLoading()
                val model: ExamResponse? = it?.data
                initData(model!!)
            }
        }
    }

    private fun initData(model: ExamResponse) {
        examResponse = model
        val adapter = ExamAdapter(model.exam!!, requireActivity(), this)
        binding.recycler.adapter = adapter
        linearLayoutManager = object : LinearLayoutManager(requireActivity()) {
            override fun canScrollVertically() = false
        }
        binding.recycler.layoutManager = linearLayoutManager
    }

    private fun changeQuestion() {
        binding.nextQuestion.setOnClickListener {
            linearLayoutManager.scrollToPositionWithOffset(position + 1, 0);
            position += 1
            btnVisibility()

        }
        binding.previousQuestion.setOnClickListener {
            linearLayoutManager.scrollToPositionWithOffset(position - 1, 0);
            position -= 1
            btnVisibility()

        }
    }

    private fun btnVisibility() {
        examProgress()
        if (position == 0) {
            binding.previousQuestion.visibility = View.GONE
            binding.examResult.visibility = View.GONE
        } else if (position == examResponse.exam!!.size - 1) {
            binding.nextQuestion.visibility = View.GONE
            binding.examResult.visibility = View.VISIBLE
            binding.previousQuestion.visibility = View.VISIBLE
        } else if (position > 0) {
            binding.examResult.visibility = View.GONE
            binding.previousQuestion.visibility = View.VISIBLE
            binding.nextQuestion.visibility = View.VISIBLE
        }
    }

    override fun onAnswerClicked(position: Int) {

    }

    private fun timer(){
        val timer = object: CountDownTimer(900000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                var time = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60
                var sec = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60
                binding.time.text = "$sec : $time"
            }
            override fun onFinish() {
                TODO("Not yet implemented")
            }
        }
        timer.start()
    }

    private fun examProgress(){
       var percentage =  ( position+1).toDouble() /examResponse.exam!!.size
        percentage *= 100
        binding.percentage.text = "${percentage.toInt()} %"
        binding.progressBar.progress = percentage.toInt()
    }
}