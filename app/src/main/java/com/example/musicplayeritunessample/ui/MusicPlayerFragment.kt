package com.example.musicplayeritunessample.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.musicplayeritunessample.R
import com.example.musicplayeritunessample.databinding.FragmentMusicPlayerBinding


class MusicPlayerFragment : Fragment() {

    private val viewModel: HomeViewModel by activityViewModels()
    private lateinit var binding: FragmentMusicPlayerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMusicPlayerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addObserver()
        binding.btnLike.setOnClickListener {
                viewModel.likedSong()
        }

        binding.btnDislike.setOnClickListener {
            viewModel.disliked()
        }
    }

    private fun addObserver() {
        viewModel.currentArtist.observe(viewLifecycleOwner, Observer {
            val imgUri = it.artwork.toUri().buildUpon().scheme("https").build()
            Log.d("obServer","Error : $it $viewModel")
            binding.tvArtistName.text = it.artistName
            binding.tvSongname.text = it.trackName
            binding.ivImage.load(imgUri){
                error(R.drawable.ic_broken_image)
                transformations(RoundedCornersTransformation(10f))
            }

        })

    }
}