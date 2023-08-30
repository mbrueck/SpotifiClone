package com.example.musicplayeritunessample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.musicplayeritunessample.adapter.SearchAdapter
import com.example.musicplayeritunessample.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {

    private val viewModel: HomeViewModel by activityViewModels()
    private lateinit var binding: FragmentSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        addObserver()


    }


    private fun addObserver() {
        viewModel.artistList.observe(viewLifecycleOwner, Observer {
            binding.rvSearch.adapter = SearchAdapter(it, viewModel)
        })

        viewModel.inputText.observe(viewLifecycleOwner, Observer {
            viewModel.getResult(it)
        })


    }
}