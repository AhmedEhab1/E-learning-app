package com.raqamyat.ecommerceclub.ui.lessons.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raqamyat.ecommerceclub.R
import com.raqamyat.ecommerceclub.databinding.FragmentAboutLessonBinding
import com.raqamyat.ecommerceclub.databinding.FragmentOnBoardingTabTwoBinding
import com.raqamyat.ecommerceclub.entities.LastEpisode
import com.raqamyat.ecommerceclub.utilities.ImageHelper


class AboutLessonFragment(private val model: LastEpisode) : Fragment() {
    private lateinit var binding: FragmentAboutLessonBinding;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutLessonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        binding.description.text = model.description
        binding.name.text = model.speaker.name
        binding.title.text = model.speaker.title
        ImageHelper().loadImage(
            requireActivity(),
            model.speaker.image,
            R.drawable.profile_icon,
            binding.image
        )
    }
}