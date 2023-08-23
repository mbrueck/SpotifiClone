package com.example.musicplayeritunessample.Adapter

import Results
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.musicplayeritunessample.databinding.ListItemMainBinding
import com.example.musicplayeritunessample.ui.HomeFragmentDirections
import com.example.musicplayeritunessample.ui.HomeViewModel

class MainAdapter(
    val dataSet: List<Results>,
    val viewModel : HomeViewModel
) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    inner class MainViewHolder(val binding: ListItemMainBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ListItemMainBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        binding.tvArtistName.text = "Warick"
        Log.d("ViewBinding", "Error: $binding")
        return MainViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return dataSet.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
       val artist = dataSet[position]
        holder.binding.tvArtistName.text = artist.artistName
        holder.binding.ivImage.setImageResource(artist.artwork)


        holder.binding.mcMainCard.setOnClickListener {
            viewModel.open(artist)
            Log.d("Adapter","$artist")

            val navController = holder.itemView.findNavController()

            navController.navigate(
                HomeFragmentDirections.actionBtnHomeToMusicPlayerFragment()
            )
        }
    }


}