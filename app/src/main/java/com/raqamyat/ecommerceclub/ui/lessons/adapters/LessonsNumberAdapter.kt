package com.raqamyat.ecommerceclub.ui.lessons.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.raqamyat.ecommerceclub.databinding.ArticlesItemBinding
import com.raqamyat.ecommerceclub.databinding.LastLessonItemBinding
import com.raqamyat.ecommerceclub.databinding.LessonsItemBinding
import com.raqamyat.ecommerceclub.databinding.OnBoardingTabOneBinding
import com.raqamyat.ecommerceclub.entities.BlogsModel
import com.raqamyat.ecommerceclub.entities.HomeModel
import com.raqamyat.ecommerceclub.entities.LastEpisode
import com.raqamyat.ecommerceclub.entities.WelcomeModel
import com.raqamyat.ecommerceclub.ui.onBoarding.fragments.OnBoardingViewHolder

class LessonsNumberAdapter(private val data: List<LastEpisode>, private val context: Activity,
                           private val listener: LessonsNumberClickListener ) : RecyclerView.Adapter<LessonsNumberViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonsNumberViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LastLessonItemBinding.inflate(inflater, parent, false)
        return LessonsNumberViewHolder(binding , listener)
    }

    override fun onBindViewHolder(holder: LessonsNumberViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, context, position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface LessonsNumberClickListener {
        fun onLessonClicked(position: Int)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}




