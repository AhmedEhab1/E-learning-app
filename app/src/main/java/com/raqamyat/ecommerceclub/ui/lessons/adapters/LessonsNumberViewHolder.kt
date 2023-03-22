package com.raqamyat.ecommerceclub.ui.lessons.adapters

import android.app.Activity
import androidx.core.content.ContextCompat
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
        if (model.status == "open"){
            binding.lock.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_material_lock_open))
        }else if (model.status == "done"){
            binding.lock.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.checkmark_circle_icon))
        }else if (model.status == "locked"){
            binding.lock.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_material_lock_outline3))
            binding.title.setTextColor(ContextCompat.getColor(context, R.color.text_color_gray));
        }else{
            binding.lock.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.checkmark_circle_icon))
        }

        binding.itemView.setOnClickListener{
            listener.onLessonClicked(position -1)
        }
    }

}