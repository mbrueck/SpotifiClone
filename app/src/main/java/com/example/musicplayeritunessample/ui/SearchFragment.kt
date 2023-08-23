package com.example.musicplayeritunessample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.musicplayeritunessample.Adapter.SearchAdapter
import com.example.musicplayeritunessample.data.model.AppRepository
import com.example.musicplayeritunessample.databinding.FragmentSearchBinding


 class SearchFragment : Fragment() {

    private lateinit var binding : FragmentSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repository = AppRepository().loadArtist()
        val recView = binding.rvSearch

        recView.adapter = SearchAdapter(repository)

        addObserver()
    }


    private fun addObserver(){


    }
}