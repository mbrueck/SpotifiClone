package com.example.musicplayeritunessample.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.musicplayeritunessample.R
import com.example.musicplayeritunessample.data.model.Track
import com.example.musicplayeritunessample.databinding.ListItemMainBinding
import com.example.musicplayeritunessample.ui.HomeFragmentDirections
import com.example.musicplayeritunessample.ui.HomeViewModel

class MainAdapter(
    val dataSet: List<Track>,
    val viewModel: HomeViewModel
) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    private lateinit var binding: ListItemMainBinding

    inner class MainViewHolder(val binding: ListItemMainBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        binding = ListItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        Log.d("ViewBinding", "Error: $binding")
        return MainViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val artist = dataSet[position]
        val imgUri = artist.artwork.toUri().buildUpon().scheme("https").build()

        holder.binding.tvArtistName.text = artist.artistName
        holder.binding.ivImageMain.load(imgUri) {
            error(R.drawable.ic_broken_image)
            transformations(RoundedCornersTransformation(10f))
        }


        holder.binding.mcMainCard.setOnClickListener {
            viewModel.open(artist)
            Log.d("Adapter", "$artist  $viewModel")

            val navController = holder.itemView.findNavController()
            navController.navigate(
                HomeFragmentDirections.actionBtnHomeToMusicPlayerFragment()
            )
        }
    }


}