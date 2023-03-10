package com.raqamyat.ecommerceclub.ui.lessons.tabs

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.raqamyat.ecommerceclub.entities.LastEpisode

class LessonsPagerAdapter(fm:FragmentManager , model : LastEpisode) : FragmentPagerAdapter(fm) {
    var data = model

        override fun getCount(): Int {
            return 3;
        }

        override fun getItem(position: Int): Fragment {
            when(position) {
                0 -> {
                    return AboutLessonFragment(data)
                }
                1 -> {
                    return LessonsTabFragment()
                } 2 -> {
                    return LessonsQuestionsTab()
                }
                else -> {
                    return AboutLessonFragment(data)
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