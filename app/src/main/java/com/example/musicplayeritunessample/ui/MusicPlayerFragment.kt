package com.example.musicplayeritunessample.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.musicplayeritunessample.R
import com.example.musicplayeritunessample.data.model.Track
import com.example.musicplayeritunessample.databinding.FragmentMusicPlayerBinding


class MusicPlayerFragment (): Fragment() {

    private val viewModel: HomeViewModel by activityViewModels()
    private lateinit var binding: FragmentMusicPlayerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMusicPlayerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?,) {
        super.onViewCreated(view, savedInstanceState)

        addObserver()
        binding.btnLike.setOnClickListener {
            viewModel.likedSong()
            binding.btnLike.setBackgroundResource(R.drawable.baseline_thumb_up_24_green)
            binding.btnDislike.setBackgroundResource(R.drawable.baseline_thumb_down_50)
        }

        binding.btnDislike.setOnClickListener {
            viewModel.disliked()
            binding.btnLike.setBackgroundResource(R.drawable.baseline_thumb_up_50)
            binding.btnDislike.setBackgroundResource(R.drawable.baseline_thumb_down_24_red)
        }

        binding.btnPlay.setOnClickListener {
            viewModel.playSong()
        }

    }

    private fun addObserver() {
        viewModel.currentArtist.observe(viewLifecycleOwner, Observer {
            Log.d("obServer", "Error : $it $viewModel")
            binding.tvArtistName.text = it.artistName
            binding.tvSongname.text = it.trackName
            binding.ivImage.load(it.artwork) {
                error(R.drawable.ic_broken_image)
                transformations(RoundedCornersTransformation(10f))
            }
        })
        viewModel.playerStatus.observe(viewLifecycleOwner, Observer {
            when (it) {

                MediaStatus.READY -> {
                    binding.btnPlay.setBackgroundResource(R.drawable.play_button)
                    binding.btnPlay.setOnClickListener {
                        viewModel.playSong()
                    }
                }

                MediaStatus.PLAYING -> {

                    binding.btnPlay.setBackgroundResource(R.drawable.breack_button)
                    binding.btnPlay.setOnClickListener {
                        viewModel.breakSong()
                    }
                }

                else -> {}
            }
        })
    }

}