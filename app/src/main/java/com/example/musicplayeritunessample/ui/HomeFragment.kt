package com.example.musicplayeritunessample.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import android.view.View
import android.view.ViewGroup
import com.example.musicplayeritunessample.R
import com.example.musicplayeritunessample.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {



    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding : FragmentHomeBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addObserver()
    }

    private fun addObserver(){

    }

}