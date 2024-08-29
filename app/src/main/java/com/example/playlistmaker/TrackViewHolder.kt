package com.example.playlistmaker

import android.content.Context
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class TrackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val trackName: TextView = itemView.findViewById(R.id.trackName)
    private val trackArtist: TextView = itemView.findViewById(R.id.trackArtist)
    private val trackAlbum: ImageView = itemView.findViewById(R.id.trackAlbum)

    fun bind(model: Track) {
        trackName.text = model.trackName
        trackArtist.text = "${model.artistName} • ${model.trackTime}"

        Glide.with(itemView)
            .load(model.artworkUrl100)
            .placeholder(R.drawable.placeholder)
            .transform(CenterCrop(), RoundedCorners(dpToPx(2f, itemView.context)))
            .into(trackAlbum)
    }

    private fun dpToPx(dp: Float, context: Context): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            context.resources.displayMetrics
        ).toInt()
    }
}