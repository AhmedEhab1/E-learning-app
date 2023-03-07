package com.raqamyat.ecommerceclub.ui.lessons

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment
import com.google.android.youtube.player.YouTubePlayerSupportFragmentXKt
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.DefaultPlayerUiController
import com.raqamyat.ecommerceclub.R
import com.raqamyat.ecommerceclub.base.BaseFragment
import com.raqamyat.ecommerceclub.databinding.LessonsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LessonsFragment : BaseFragment(), YouTubePlayer.OnInitializedListener {
    private lateinit var binding: LessonsFragmentBinding
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
//        val youTubePlayerFragment: YouTubePlayerSupportFragmentXKt = YouTubePlayerSupportFragmentXKt.newInstance()
//        val transaction: FragmentTransaction = childFragmentManager.beginTransaction()
//        transaction.replace(R.id.youtube, youTubePlayerFragment).commit()

//        val youTubePlayerFragment =
//            requireActivity().supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as? YouTubePlayerSupportFragmentXKt
//       //vv youTubePlayerFragment?.initialize(apiKey, this)


//        youTubePlayerFragment.initialize(apiKey, object : YouTubePlayer.OnInitializedListener {
//            override fun onInitializationSuccess(
//
//                provider: YouTubePlayer.Provider?,
//                player: YouTubePlayer?,
//                bln: Boolean
//            ) {
//                Log.e("onInitializationSuccess", "onInitializationSuccess: " )
//
//                player?.loadVideo("Hce74cEAAaE")
//                player?.play()
//            }
//
//            override fun onInitializationFailure(
//                provider: YouTubePlayer.Provider?,
//                result: YouTubeInitializationResult?
//            ) {
//                Log.e("error", "onInitializationFailure: " )
////                Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_SHORT).show()
//            }
//        })


    }

    override fun onInitializationSuccess(
        p0: YouTubePlayer.Provider?,
        p1: YouTubePlayer?,
        p2: Boolean
    ) {
        TODO("Not yet implemented")

    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {
        TODO("Not yet implemented")
    }


    fun youtube() {

        val thirdPartyYouTubePlayerView =
            requireView().findViewById<YouTubePlayerView>(R.id.third_party_player_view)

        thirdPartyYouTubePlayerView.enableAutomaticInitialization =
            false // We set it to false because we init it manually


        val listener: YouTubePlayerListener = object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer) {
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

                val videoId = "Hce74cEAAaE"
                youTubePlayer.cueVideo(videoId, 0F)
            }
        }

        // Disable iFrame UI
        val options: IFramePlayerOptions = IFramePlayerOptions.Builder().controls(0).build()
        thirdPartyYouTubePlayerView.initialize(listener, options)
//        listener.on()

    }
}