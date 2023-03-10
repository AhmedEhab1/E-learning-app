package com.raqamyat.ecommerceclub.ui.lessons

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.raqamyat.ecommerceclub.databinding.ArticlesItemBinding
import com.raqamyat.ecommerceclub.databinding.LessonsItemBinding
import com.raqamyat.ecommerceclub.databinding.OnBoardingTabOneBinding
import com.raqamyat.ecommerceclub.entities.BlogsModel
import com.raqamyat.ecommerceclub.entities.HomeModel
import com.raqamyat.ecommerceclub.entities.LastEpisode
import com.raqamyat.ecommerceclub.entities.WelcomeModel
import com.raqamyat.ecommerceclub.ui.onBoarding.fragments.OnBoardingViewHolder

class LessonsAdapter(private val data: List<LastEpisode>, private val context: Activity,
                     private val listener: LessonsClickListener ) : RecyclerView.Adapter<LessonsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LessonsItemBinding.inflate(inflater, parent, false)
        return LessonsViewHolder(binding , listener)
    }

    override fun onBindViewHolder(holder: LessonsViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, context, position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface LessonsClickListener {
        fun onArticlesItemClick(position: Int)
    }


}




