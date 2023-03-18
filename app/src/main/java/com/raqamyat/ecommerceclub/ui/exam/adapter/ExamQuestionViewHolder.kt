package com.raqamyat.ecommerceclub.ui.exam.adapter

import android.app.Activity
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.raqamyat.ecommerceclub.R
import com.raqamyat.ecommerceclub.databinding.ExamItemBinding
import com.raqamyat.ecommerceclub.databinding.ExamQuestionItemBinding
import com.raqamyat.ecommerceclub.databinding.LastLessonItemBinding
import com.raqamyat.ecommerceclub.databinding.LessonsItemBinding
import com.raqamyat.ecommerceclub.entities.AnswersModel
import com.raqamyat.ecommerceclub.entities.ExamModel
import com.raqamyat.ecommerceclub.entities.LastEpisode
import com.raqamyat.ecommerceclub.ui.lessons.tabs.LessonsPagerAdapter
import com.raqamyat.ecommerceclub.ui.profile.ProfilePagerAdapter

class ExamQuestionViewHolder(
    private val binding: ExamQuestionItemBinding,
    private val listener: ExamAdapter.AnswersListener
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(model: AnswersModel, context: Activity, position: Int) {
        binding.answer.text = model.title

    }

}