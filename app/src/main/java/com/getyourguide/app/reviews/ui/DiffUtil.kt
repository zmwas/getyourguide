package com.getyourguide.app.reviews.ui

import androidx.recyclerview.widget.DiffUtil
import com.getyourguide.app.reviews.models.Review

class DiffUtil : DiffUtil.ItemCallback<Review>() {

    override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem == newItem
    }
}
