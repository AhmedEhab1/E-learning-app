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
    private var listener: LessonsAdapter.LessonsClickListener
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(model: List<LastEpisode>, context: Activity, position: Int , listener: LessonsAdapter.LessonsClickListener) {
        this.listener = listener
        binding.title.text = model[position].title
        val time = model[position].time + " دقيقة  "
        binding.time.text = time
        initTabLayout(context, model, position)
    }

    private fun initTabLayout(context: Activity, model: List<LastEpisode> , position : Int) {
        binding.tabLayout.id = binding.tabLayout.id+position
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
        binding.viewPager.id = binding.viewPager.id+position
        binding.viewPager.adapter = LessonsPagerAdapter(fragmentManager, model, position , listener)

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