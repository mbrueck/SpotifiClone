package com.example.musicplayeritunessample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.musicplayeritunessample.adapter.MainAdapter
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


        viewModel.getTrackList()
        addObserver()
    }

    fun addObserver() {
        viewModel.mainSideList.observe(viewLifecycleOwner, Observer {
            val recView = binding.rvMainPage
            recView.adapter = MainAdapter(it, viewModel)

        })

        viewModel.genre.observe(viewLifecycleOwner, Observer {
            binding.tvGenreMain.setText(it)
        })
    }


}