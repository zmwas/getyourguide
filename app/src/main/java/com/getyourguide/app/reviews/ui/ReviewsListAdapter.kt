package com.getyourguide.app.reviews.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import com.getyourguide.app.R
import com.getyourguide.app.databinding.ReviewItemLayoutBinding
import com.getyourguide.app.reviews.models.Review

class ReviewsListAdapter : PagedListAdapter<Review, ReviewsViewHolder>(DiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ReviewItemLayoutBinding = DataBindingUtil
            .inflate(inflater, R.layout.review_item_layout, parent, false)
        return ReviewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewsViewHolder, position: Int) {
        val review = getItem(position)
        holder.bind(review)
    }
}
