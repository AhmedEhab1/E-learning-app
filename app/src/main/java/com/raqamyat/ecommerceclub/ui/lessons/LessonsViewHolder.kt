package com.raqamyat.ecommerceclub.ui.lessons

import android.app.Activity
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.raqamyat.ecommerceclub.R
import com.raqamyat.ecommerceclub.databinding.LessonsItemBinding
import com.raqamyat.ecommerceclub.entities.LastEpisode
import com.raqamyat.ecommerceclub.ui.lessons.tabs.LessonsPagerAdapter
import com.raqamyat.ecommerceclub.ui.profile.ProfilePagerAdapter

class LessonsViewHolder(
    private val binding: LessonsItemBinding,
    private val listener: LessonsAdapter.LessonsClickListener
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(model: LastEpisode, context: Activity, position: Int) {
        binding.title.text = model.title
        val time = model.time + " دقيقة  "
        binding.time.text = time
        initTabLayout(context, model)

    }

    private fun initTabLayout(context: Activity, model: LastEpisode) {
        binding.tabLayout.addTab(
            binding.tabLayout.newTab().setText(context.getString(R.string.about_lesson))
        )
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(context.getString(R.string.lessons)))
        binding.tabLayout.addTab(
            binding.tabLayout.newTab().setText(context.getString(R.string.questions_about_lesson))
        )
        binding.tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        val fragmentManager: FragmentManager =
            (context as FragmentActivity).supportFragmentManager
        binding.viewPager.adapter = LessonsPagerAdapter(fragmentManager, model)

        binding.viewPager.currentItem = 0
        binding.viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout))
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }
}