package com.raqamyat.ecommerceclub.ui.exam

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.raqamyat.ecommerceclub.R
import com.raqamyat.ecommerceclub.base.BaseFragment
import com.raqamyat.ecommerceclub.databinding.ExamFragmentBinding
import com.raqamyat.ecommerceclub.entities.AnswersModel
import com.raqamyat.ecommerceclub.entities.AnswersRequest
import com.raqamyat.ecommerceclub.entities.ExamAnswersResponse
import com.raqamyat.ecommerceclub.entities.ExamResponse
import com.raqamyat.ecommerceclub.ui.exam.adapter.ExamAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit


@AndroidEntryPoint
class ExamFragment : BaseFragment(), ExamAdapter.AnswersListener {
    private lateinit var binding: ExamFragmentBinding
    private val viewModel: ExamViewModel by viewModels()
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var position = 0
    private lateinit var examResponse: ExamResponse
    private val answersRequest : ArrayList<AnswersRequest> = ArrayList()

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
        answersResponse()
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

    private fun answersResponse() {
        lifecycleScope.launch {
            viewModel.examAnswersResponse.observe(viewLifecycleOwner) {
                dismissLoading()
                val model: ExamAnswersResponse = it?.data!!
                var bundle = Bundle()
                bundle.putSerializable("model", model as java.io.Serializable)
                if (model.isPass!!){
                    Navigation.findNavController(requireView()).navigate(
                        R.id.action_examFragment_to_examResultSuccessFragment, bundle
                    )
                }else{
                    Navigation.findNavController(requireView()).navigate(
                        R.id.action_examFragment_to_examResultFailFragment, bundle
                    )
                }
            }
        }
    }

    private fun initData(model: ExamResponse) {
        examResponse = model
        crateAnswersRequest()
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
        binding.examResult.setOnClickListener {
            viewModel.examAnswers(answersRequest)
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

    private fun crateAnswersRequest(){
        for (i in examResponse.exam!!.indices) {
            answersRequest.add(AnswersRequest(examResponse.exam!![i].id,null ))
        }
    }

    override fun onAnswerClicked(request: AnswersRequest) {
        answersRequest[position].answer_id = request.answer_id
        answersRequest[position].question_id = request.question_id
    }
}