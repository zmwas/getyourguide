package com.getyourguide.app.reviews.ui

import androidx.recyclerview.widget.RecyclerView
import com.getyourguide.app.databinding.ReviewItemLayoutBinding
import com.getyourguide.app.reviews.models.Review

class ReviewsViewHolder(private val binding: ReviewItemLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(review: Review?) {
        binding.review = review
    }
}