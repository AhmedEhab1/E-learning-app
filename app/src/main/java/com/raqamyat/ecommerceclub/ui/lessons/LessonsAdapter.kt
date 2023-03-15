package com.raqamyat.ecommerceclub.ui.lessons

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.raqamyat.ecommerceclub.databinding.LessonsItemBinding
import com.raqamyat.ecommerceclub.entities.LastEpisode

class LessonsAdapter(private val data: List<LastEpisode>, private val context: Activity,
                     private val listener: LessonsClickListener ) : RecyclerView.Adapter<LessonsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LessonsItemBinding.inflate(inflater, parent, false)
        return LessonsViewHolder(binding , listener)
    }

    override fun onBindViewHolder(holder: LessonsViewHolder, position: Int) {
        holder.bind(data, context, position, listener)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface LessonsClickListener {
        fun onNextLessonClicked(position: Int)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

}




