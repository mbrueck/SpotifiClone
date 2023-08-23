package com.example.musicplayeritunessample.Adapter

import Results
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

import com.example.musicplayeritunessample.databinding.ListItemTrackBinding


class SearchAdapter(
    val dataSet: List<Results>
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
            holder.binding.tvArtistName.text = artist.artistName
            holder.binding.tvAlbumName.text = artist.trackName
            holder.binding.ivImage.setImageResource(artist.artwork)

    }

}