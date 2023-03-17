package com.raqamyat.ecommerceclub.ui.lessons.adapters

import android.app.Activity
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.raqamyat.ecommerceclub.R
import com.raqamyat.ecommerceclub.databinding.LessonsItemBinding
import com.raqamyat.ecommerceclub.databinding.LessonsQuestionItemBinding
import com.raqamyat.ecommerceclub.entities.LastEpisode
import com.raqamyat.ecommerceclub.entities.Question
import com.raqamyat.ecommerceclub.ui.lessons.tabs.LessonsPagerAdapter
import com.raqamyat.ecommerceclub.ui.profile.ProfilePagerAdapter
import com.raqamyat.ecommerceclub.utilities.ImageHelper

class LessonsQuestionsHolder(
    private val binding: LessonsQuestionItemBinding,
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(model: List<Question>, context: Activity, position: Int ) {
        binding.userName.text = model[position].user_name
        binding.userCommentDate.text = model[position].date
        binding.userComment.text = model[position].question
        ImageHelper().loadImage(
            context,
            model[position].user_image,
            R.drawable.profile_icon,
            binding.userImage
        )


    }

}