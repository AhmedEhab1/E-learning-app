package com.raqamyat.ecommerceclub.ui.lessons.tabs

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.raqamyat.ecommerceclub.entities.LastEpisode
import com.raqamyat.ecommerceclub.ui.lessons.LessonsAdapter

class LessonsPagerAdapter(
    fm: FragmentManager,
    model: List<LastEpisode>,
    private var positionAdapter: Int,
    private var listener: LessonsAdapter.LessonsClickListener
) : FragmentPagerAdapter(fm) {
    var data = model

    override fun getCount(): Int {
        return 3;
    }

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return AboutLessonFragment(data, listener , positionAdapter)
            }
            1 -> {
                return LessonsTabFragment(data, listener , positionAdapter)
            }
            2 -> {
                return LessonsQuestionsTab(data[positionAdapter])
            }
            else -> {
                return AboutLessonFragment(data, listener , positionAdapter)
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> {
                return "Tab 1"
            }
            1 -> {
                return "Tab 2"
            }
            2 -> {
                return "Tab 3"
            }
        }
        return super.getPageTitle(position)
    }

}