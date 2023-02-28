package com.raqamyat.ecommerceclub.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.raqamyat.ecommerceclub.R
import com.raqamyat.ecommerceclub.base.BaseFragment
import com.raqamyat.ecommerceclub.databinding.ForgetPasswordFragmentBinding
import com.raqamyat.ecommerceclub.databinding.HomeFragmentBinding
import com.raqamyat.ecommerceclub.entities.HomeModel
import com.raqamyat.ecommerceclub.ui.onBoarding.adapter.OnboardingAdapter
import com.raqamyat.ecommerceclub.ui.profile.viewModels.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment(), ArticlesAdapter.ArticlesClickListener {
    private lateinit var binding: HomeFragmentBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun init(){
        binding.userName.text = getPreferencesHelper().getString("name")
        errorMessage()
        getHome()
        requestResponse()
    }

    private fun getHome(){
        showLoading()
        viewModel.getHome()
    }

    private fun errorMessage() {
        lifecycleScope.launch {
            viewModel.errorMessage.observe(viewLifecycleOwner) {
                showErrorDialog(it.toString())
            }
        }
    }

    private fun requestResponse() {
        lifecycleScope.launch {
            viewModel.response.observe(viewLifecycleOwner) {
                dismissLoading()
               val model : HomeModel? = it?.data
                initData(model!!)
            }
        }
    }

    private fun initData(model : HomeModel){
        binding.watchedEpisodesCount.text = model.watched_episodes_count
        binding.title.text = model.last_episode?.title

        val adapter = ArticlesAdapter(model.blogs!!, requireActivity(), this)
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.recycler.onFlingListener = null
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.recycler)
    }

    override fun onArticlesItemClick(position: Int) {
    }

}