package com.getyourguide.app.reviews.ui

import androidx.recyclerview.widget.DiffUtil
import com.getyourguide.app.reviews.models.Reviews

class DiffUtil : DiffUtil.ItemCallback<Reviews>() {

    override fun areItemsTheSame(oldItem: Reviews, newItem: Reviews): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Reviews, newItem: Reviews): Boolean {
        return oldItem == newItem
    }
}
