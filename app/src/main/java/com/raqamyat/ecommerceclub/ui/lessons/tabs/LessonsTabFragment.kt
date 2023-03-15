package com.raqamyat.ecommerceclub.ui.lessons.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.raqamyat.ecommerceclub.R
import com.raqamyat.ecommerceclub.databinding.FragmentAboutLessonBinding
import com.raqamyat.ecommerceclub.databinding.LessonsTabFragmentBinding
import com.raqamyat.ecommerceclub.entities.LastEpisode
import com.raqamyat.ecommerceclub.ui.lessons.LessonsAdapter
import com.raqamyat.ecommerceclub.ui.lessons.adapters.LessonsNumberAdapter


class LessonsTabFragment(private val model: List<LastEpisode> ,
                         private var listener: LessonsAdapter.LessonsClickListener,
                         private var position: Int) : Fragment(),
    LessonsNumberAdapter.LessonsNumberClickListener {
    private lateinit var binding : LessonsTabFragmentBinding;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = LessonsTabFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        initAdapter(model)
    }

    private fun initAdapter(model : List<LastEpisode>){
        val adapter = LessonsNumberAdapter(model, requireActivity(), this)
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager =LinearLayoutManager(requireActivity() , LinearLayoutManager.VERTICAL , false)
    }

    override fun onLessonClicked(position: Int) {
        listener.onNextLessonClicked(position)
    }
}