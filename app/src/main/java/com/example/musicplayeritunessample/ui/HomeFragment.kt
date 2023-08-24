package com.example.musicplayeritunessample.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.musicplayeritunessample.Adapter.MainAdapter
import com.example.musicplayeritunessample.Remote.TrackApi
import com.example.musicplayeritunessample.data.model.AppRepository
import com.example.musicplayeritunessample.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {


    private val viewModel: HomeViewModel by activityViewModels()
    private lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val recView = binding.rvMainPage

        recView.adapter = MainAdapter(AppRepository(TrackApi).loadArtist(),viewModel)



    }



}