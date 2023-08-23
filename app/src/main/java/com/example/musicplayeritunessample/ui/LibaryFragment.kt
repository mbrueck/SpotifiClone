package com.example.musicplayeritunessample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.musicplayeritunessample.Adapter.SearchAdapter
import com.example.musicplayeritunessample.data.model.AppRepository
import com.example.musicplayeritunessample.databinding.FragmentLibaryBinding

class LibaryFragment : Fragment() {

    private lateinit var binding: FragmentLibaryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLibaryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repository = AppRepository().loadArtist()
        val recView = binding.rvLibary

        recView.adapter = SearchAdapter(repository)
        addObserver()
    }

    private fun addObserver() {


    }
}