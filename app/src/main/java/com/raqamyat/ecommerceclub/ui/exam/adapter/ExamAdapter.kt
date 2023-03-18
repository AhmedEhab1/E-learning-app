package com.raqamyat.ecommerceclub.ui.exam.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.raqamyat.ecommerceclub.databinding.*
import com.raqamyat.ecommerceclub.entities.*
import com.raqamyat.ecommerceclub.ui.onBoarding.fragments.OnBoardingViewHolder

class ExamAdapter(private val data: List<ExamModel>, private val context: Activity ,private val listener : AnswersListener)
    : RecyclerView.Adapter<ExamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExamViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ExamItemBinding.inflate(inflater, parent, false)
        return ExamViewHolder(binding ,  listener)
    }

    override fun onBindViewHolder(holder: ExamViewHolder, position: Int) {
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

    interface AnswersListener {
        fun onAnswerClicked(position: Int)
    }
}




