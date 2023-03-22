package com.raqamyat.ecommerceclub.ui.lessons.tabs

import android.content.Intent
import android.net.Uri
import android.opengl.Visibility
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.parseAsHtml
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.raqamyat.ecommerceclub.R
import com.raqamyat.ecommerceclub.databinding.FragmentAboutLessonBinding
import com.raqamyat.ecommerceclub.entities.LastEpisode
import com.raqamyat.ecommerceclub.ui.lessons.LessonsAdapter
import com.raqamyat.ecommerceclub.utilities.ImageHelper
import java.text.FieldPosition


class AboutLessonFragment(
    private val model: List<LastEpisode>,
    private var listener: LessonsAdapter.LessonsClickListener,
    private var position: Int
) : Fragment() {
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
        binding.description.text = model[position].description?.parseAsHtml()
        binding.name.text = model[position].speaker?.name
        binding.title.text = model[position].speaker?.title
        binding.image.clipToOutline = true
        ImageHelper().loadImage(
            requireActivity(),
            model[position].speaker?.image,
            R.drawable.profile_icon,
            binding.image
        )
        openLinkedIn()
        btnListeners()
        btnVisibility()
    }

    private fun openLinkedIn() {
        binding.linkIn.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(model[position].speaker?.linkedin)
            startActivity(i)
        }
    }

    private fun btnListeners() {
        binding.nextLesson.setOnClickListener {
            listener.onNextLessonClicked(position)
        }
        binding.previewsLesson.setOnClickListener {
            listener.onBackLessonClicked(position)
        }
        binding.obtainingCertificate.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(
                R.id.action_lessonsFragment_to_examInstructions
            )
        }
    }

    private fun btnVisibility() {
        if (position == 0) {
            binding.previewsLesson.visibility= View.GONE
            binding.obtainingCertificate.visibility= View.GONE
        } else if (position == model.size -1){
            binding.nextLesson.visibility= View.GONE
            binding.obtainingCertificate.visibility= View.VISIBLE
            binding.previewsLesson.visibility= View.VISIBLE
        }else if (position > 0){
            binding.obtainingCertificate.visibility= View.GONE
            binding.previewsLesson.visibility= View.VISIBLE
        }
    }
}