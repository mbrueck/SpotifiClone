package com.example.musicplayeritunessample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.musicplayeritunessample.R
import com.example.musicplayeritunessample.data.model.Track

import com.example.musicplayeritunessample.databinding.ListItemTrackBinding
import com.example.musicplayeritunessample.ui.HomeViewModel
import com.example.musicplayeritunessample.ui.LibaryFragment
import com.example.musicplayeritunessample.ui.LibaryFragmentDirections
import com.example.musicplayeritunessample.ui.SearchFragmentDirections


class SearchAdapter(
    val dataSet: List<Track>,
    val viewModel: HomeViewModel,
) : RecyclerView.Adapter<SearchAdapter.ListItemViewHolder>() {
    inner class ListItemViewHolder(val binding: ListItemTrackBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val binding =
            ListItemTrackBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListItemViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        val artist = dataSet[position]
        val imgUri = artist.previewUrl.toUri().buildUpon().scheme("https").build()

        holder.binding.tvArtistName.text = artist.artistName
        holder.binding.tvAlbumName.text = artist.trackName

        holder.binding.ivImageSearch.load(imgUri) {
            error(R.drawable.ic_broken_image)
            transformations(RoundedCornersTransformation(10f))
        }

        holder.binding.mcItem.setOnClickListener {
            viewModel.open(artist)
            val navController = holder.itemView.findNavController()
            if (R.id.btn_search == navController.currentDestination?.id) {
                navController.navigate(SearchFragmentDirections.actionBtnSearchToMusicPlayerFragment())
            } else {
                navController.navigate(LibaryFragmentDirections.actionBtnLibaryToMusicPlayerFragment())
            }
        }


    }

}