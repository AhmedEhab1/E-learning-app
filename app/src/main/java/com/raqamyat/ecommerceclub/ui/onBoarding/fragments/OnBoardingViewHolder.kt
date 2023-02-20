package com.raqamyat.ecommerceclub.ui.onBoarding.fragments

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.raqamyat.ecommerceclub.R
import com.raqamyat.ecommerceclub.databinding.OnBoardingTabOneBinding
import com.raqamyat.ecommerceclub.entities.WelcomeModel
import com.raqamyat.ecommerceclub.ui.onBoarding.adapter.OnboardingAdapter
import com.raqamyat.ecommerceclub.utilities.ImageHelper

class OnBoardingViewHolder(private val binding: OnBoardingTabOneBinding ,  private val listener: OnboardingAdapter.ListItemClickListener) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(welcomeModel: WelcomeModel, context: Context, position: Int) {
        binding.title.text = welcomeModel.title
        binding.description.text = welcomeModel.description
        ImageHelper().loadImage(
            context,
            welcomeModel.image,
            R.drawable.profile_icon,
            binding.image
        )

        if (position == 2){
            binding.pageNum.visibility = View.GONE
            binding.start.visibility = View.VISIBLE
        }

        if (position ==0){
            binding.page1.backgroundTintList = ContextCompat.getColorStateList(context,R.color.purple);
        }else if (position ==1){
            binding.page2.backgroundTintList = ContextCompat.getColorStateList(context,R.color.purple);
        }

        binding.start.setOnClickListener{
            listener.onListItemClick(position)
        }
    }
}