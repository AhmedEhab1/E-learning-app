package com.raqamyat.ecommerceclub.ui.exam.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.raqamyat.ecommerceclub.databinding.*
import com.raqamyat.ecommerceclub.entities.*
import com.raqamyat.ecommerceclub.ui.onBoarding.fragments.OnBoardingViewHolder

class ExamQuestionAdapter(private val data: List<AnswersModel>, private val context: Activity, private val listener : ExamAdapter.AnswersListener)
    : RecyclerView.Adapter<ExamQuestionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExamQuestionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ExamQuestionItemBinding.inflate(inflater, parent, false)
        return ExamQuestionViewHolder(binding ,  listener)
    }

    override fun onBindViewHolder(holder: ExamQuestionViewHolder, position: Int) {
        holder.bind(data[position], context, position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

}




