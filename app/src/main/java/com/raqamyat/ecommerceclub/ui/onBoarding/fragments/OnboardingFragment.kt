package com.raqamyat.ecommerceclub.ui.onBoarding.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.raqamyat.ecommerceclub.R
import com.raqamyat.ecommerceclub.databinding.OnboardingFragmentBinding
import com.raqamyat.ecommerceclub.entities.WelcomeModel
import com.raqamyat.ecommerceclub.ui.onBoarding.adapter.OnboardingAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class OnboardingFragment : Fragment(), OnboardingAdapter.ListItemClickListener {
    private lateinit var binding: OnboardingFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = OnboardingFragmentBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val list: List<WelcomeModel> = arguments?.getSerializable("model") as List<WelcomeModel>

        val adapter = OnboardingAdapter(list, requireActivity(), this)
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, true)
        binding.recycler.onFlingListener = null
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.recycler)
    }

    override fun onListItemClick(position: Int) {
        Navigation.findNavController(requireView()).navigate(
            R.id.action_onboardingFragment_to_authFragment
        )
    }

}