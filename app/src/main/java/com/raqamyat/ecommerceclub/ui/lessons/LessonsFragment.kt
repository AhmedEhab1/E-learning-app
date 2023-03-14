package com.raqamyat.ecommerceclub.ui.lessons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.youtube.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.DefaultPlayerUiController
import com.raqamyat.ecommerceclub.R
import com.raqamyat.ecommerceclub.base.BaseFragment
import com.raqamyat.ecommerceclub.databinding.LessonsFragmentBinding
import com.raqamyat.ecommerceclub.entities.LastEpisode
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LessonsFragment : BaseFragment() , LessonsAdapter.LessonsClickListener {
    private lateinit var binding: LessonsFragmentBinding
    private val viewModel: LessonsViewModel by viewModels()
   private lateinit var youTubePlayer: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
    var onInitializedListener: YouTubePlayer.OnInitializedListener? = null
    var apiKey = "AIzaSyA4czZfjxZsJCXnTOxANReSrL_6su6PmE4"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        youtube()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = LessonsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun init() {
        showLoading()
        viewModel.getLessons()
        errorMessage()
        requestResponse()
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
                initAdapter(it!!.data)
                youTubePlayer.cueVideo(it.data[0].video_id, 0F)
            }
        }
    }

    private fun initAdapter(model : List<LastEpisode>){
        val adapter = LessonsAdapter(model, requireActivity(), this)
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager =object : LinearLayoutManager(requireActivity())
        { override fun canScrollVertically() = false }
    }

    fun youtube() {
        val thirdPartyYouTubePlayerView =
            requireView().findViewById<YouTubePlayerView>(R.id.third_party_player_view)
        thirdPartyYouTubePlayerView.enableAutomaticInitialization =
            false // We set it to false because we init it manually

        val listener: YouTubePlayerListener = object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer1: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer) {
                youTubePlayer = youTubePlayer1
                // We're using pre-made custom ui
                val defaultPlayerUiController =
                    DefaultPlayerUiController(thirdPartyYouTubePlayerView, youTubePlayer)
                defaultPlayerUiController.showFullscreenButton(true)

                // When the video is in full-screen, cover the entire screen
                defaultPlayerUiController.setFullScreenButtonClickListener {
                    if (thirdPartyYouTubePlayerView.isFullScreen()) {
                        thirdPartyYouTubePlayerView.exitFullScreen()
                        requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
                        // Show ActionBar

                    } else {
                        thirdPartyYouTubePlayerView.enterFullScreen()
                        requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
                        // Hide ActionBar

                    }
                }
                thirdPartyYouTubePlayerView.setCustomPlayerUi(defaultPlayerUiController.rootView)
//                val videoId = "nhEYtMGCjzI"
//                youTubePlayer.cueVideo(videoId, 0F)

            }
        }
        // Disable iFrame UI
        val options: IFramePlayerOptions = IFramePlayerOptions.Builder().controls(0).build()
        thirdPartyYouTubePlayerView.initialize(listener, options)
//        listener.on()

    }

    override fun onArticlesItemClick(position: Int) {
        TODO("Not yet implemented")
    }
}