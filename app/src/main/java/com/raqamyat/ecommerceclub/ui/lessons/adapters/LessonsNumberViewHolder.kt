package com.raqamyat.ecommerceclub.ui.lessons.adapters

import android.app.Activity
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.raqamyat.ecommerceclub.R
import com.raqamyat.ecommerceclub.databinding.LastLessonItemBinding
import com.raqamyat.ecommerceclub.databinding.LessonsItemBinding
import com.raqamyat.ecommerceclub.entities.LastEpisode
import com.raqamyat.ecommerceclub.ui.lessons.tabs.LessonsPagerAdapter
import com.raqamyat.ecommerceclub.ui.profile.ProfilePagerAdapter

class LessonsNumberViewHolder(
    private val binding: LastLessonItemBinding,
    private val listener: LessonsNumberAdapter.LessonsNumberClickListener
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(model: LastEpisode, context: Activity, position: Int) {
        binding.title.text = model.title
        binding.itemView.setOnClickListener{
            listener.onLessonClicked(position -1)
        }
    }

}