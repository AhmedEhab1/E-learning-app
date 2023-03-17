package com.raqamyat.ecommerceclub.ui.lessons.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.raqamyat.ecommerceclub.databinding.*
import com.raqamyat.ecommerceclub.entities.*
import com.raqamyat.ecommerceclub.ui.onBoarding.fragments.OnBoardingViewHolder

class LessonQuestionsAdapter(private val data: List<Question>, private val context: Activity)
    : RecyclerView.Adapter<LessonsQuestionsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonsQuestionsHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LessonsQuestionItemBinding.inflate(inflater, parent, false)
        return LessonsQuestionsHolder(binding )
    }

    override fun onBindViewHolder(holder: LessonsQuestionsHolder, position: Int) {
        holder.bind(data, context, position)
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




