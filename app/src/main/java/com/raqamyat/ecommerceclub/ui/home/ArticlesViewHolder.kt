package com.raqamyat.ecommerceclub.ui.home

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.raqamyat.ecommerceclub.R
import com.raqamyat.ecommerceclub.databinding.ArticlesItemBinding
import com.raqamyat.ecommerceclub.entities.BlogsModel
import com.raqamyat.ecommerceclub.entities.HomeModel
import com.raqamyat.ecommerceclub.entities.WelcomeModel
import com.raqamyat.ecommerceclub.utilities.ImageHelper

class ArticlesViewHolder(private val binding: ArticlesItemBinding, private val listener: ArticlesAdapter.ArticlesClickListener) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(model: BlogsModel, context: Context, position: Int) {
        binding.title.text = model.title
        binding.date.text = model.date
        binding.author.text = model.author
        ImageHelper().loadImage(
            context,
            model.image,
            R.drawable.profile_icon,
            binding.image
        )


        binding.viewHolder.setOnClickListener{
            listener.onArticlesItemClick(position)
        }
    }
}