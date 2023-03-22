package com.raqamyat.ecommerceclub.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.raqamyat.ecommerceclub.databinding.ArticlesItemBinding
import com.raqamyat.ecommerceclub.databinding.OnBoardingTabOneBinding
import com.raqamyat.ecommerceclub.entities.BlogsModel
import com.raqamyat.ecommerceclub.entities.HomeModel
import com.raqamyat.ecommerceclub.entities.WelcomeModel
import com.raqamyat.ecommerceclub.ui.onBoarding.fragments.OnBoardingViewHolder

class ArticlesAdapter(private val data: List<BlogsModel>, private val context: Context, private val listener: ArticlesClickListener ) : RecyclerView.Adapter<ArticlesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ArticlesItemBinding.inflate(inflater, parent, false)
        return ArticlesViewHolder(binding , listener)
    }

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, context, position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface ArticlesClickListener {
        fun onArticlesItemClick(position: String)
    }
}




