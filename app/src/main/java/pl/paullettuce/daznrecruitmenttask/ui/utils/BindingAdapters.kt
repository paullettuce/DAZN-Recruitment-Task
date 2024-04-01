package pl.paullettuce.daznrecruitmenttask.ui.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import pl.paullettuce.daznrecruitmenttask.R

@BindingAdapter("loadImage")
fun ImageView.loadImage(imageUrl: String) = load(
    imageUrl,
    builder = {
        placeholder(R.drawable.event_thumbnail_placeholder)
    }
)
