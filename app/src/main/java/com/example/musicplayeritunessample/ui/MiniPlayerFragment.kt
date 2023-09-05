package com.example.musicplayeritunessample.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.musicplayeritunessample.R
import com.example.musicplayeritunessample.databinding.FragmentMiniPlayerBinding

private val TAG = "MINIPLAYERFRAGMENT"

class MiniPlayerFragment : Fragment() {
    private lateinit var binding: FragmentMiniPlayerBinding
    private val viewModel: HomeViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMiniPlayerBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addObserver()

        binding.btnPlay.setOnClickListener {
            viewModel.playOrPauseSong()
        }
        binding.btnPlayerNextTrack.setOnClickListener {
            viewModel.skipForward()
            Log.d(TAG, "skip backwoard :${viewModel.skipForward()}")
        }
        binding.btnPlayerPrevTrack.setOnClickListener {
            viewModel.skipBackward()
            Log.d(TAG, "skip backwoard :${viewModel.skipBackward()}")
        }
        binding.miniPlayer.setOnClickListener {
            viewModel.open(viewModel.selectedSong.value!!)
            findNavController().navigate(R.id.musicPlayerFragment)
        }
    }

    private fun addObserver() {

        viewModel.playerStatus.observe(viewLifecycleOwner, Observer {
            when (it) {
                MediaStatus.LOADING -> binding.miniPlayer.visibility = View.GONE
                MediaStatus.READY -> {
                    binding.miniPlayer.visibility = View.VISIBLE
                    binding.btnPlay.setBackgroundResource(R.drawable.play_button)
                }
                MediaStatus.PLAYING -> {
                    binding.miniPlayer.visibility = View.VISIBLE
                    binding.btnPlay.setBackgroundResource(R.drawable.breack_button)
                }

                MediaStatus.FINISHED -> binding.miniPlayer.visibility = View.GONE
            }
        })
        viewModel.selectedSong.observe(viewLifecycleOwner, Observer {
            binding.tvMiniplayerArtistName.text = it.artistName
            binding.tvSongname.text = it.trackName
            binding.ivPlayerplayer.load(it.artwork)
            binding.progressBar.max = it.trackTimeSeconds
        })
        viewModel.songTime.observe(viewLifecycleOwner, Observer {
            binding.progressBar.progress = it / 1000
        })
    }
}
