package com.raqamyat.ecommerceclub.ui.onBoarding.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.raqamyat.ecommerceclub.databinding.OnBoardingTabOneBinding
import com.raqamyat.ecommerceclub.entities.WelcomeModel
import com.raqamyat.ecommerceclub.ui.onBoarding.fragments.OnBoardingViewHolder

class OnboardingAdapter(private val data: List<WelcomeModel>,private val context: Context,  private val listener: ListItemClickListener ) : RecyclerView.Adapter<OnBoardingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = OnBoardingTabOneBinding.inflate(inflater, parent, false)
        return OnBoardingViewHolder(binding , listener)
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, context, position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface ListItemClickListener {
        fun onListItemClick(position: Int)
    }
}




