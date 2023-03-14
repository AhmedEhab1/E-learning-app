package com.raqamyat.ecommerceclub.ui.lessons.tabs

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.raqamyat.ecommerceclub.entities.LastEpisode

class LessonsPagerAdapter(fm:FragmentManager , model : List<LastEpisode> , position: Int) : FragmentPagerAdapter(fm) {
    var data = model

        override fun getCount(): Int {
            return 3;
        }

        override fun getItem(position: Int): Fragment {
            when(position) {
                0 -> {
                    return AboutLessonFragment(data[position])
                }
                1 -> {
                    return LessonsTabFragment(data)
                } 2 -> {
                    return LessonsQuestionsTab(data[position].questions)
                }
                else -> {
                    return AboutLessonFragment(data[position])
                }
            }
        }

        override fun getPageTitle(position: Int): CharSequence? {
            when(position) {
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