package com.getyourguide.app.reviews.ui

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import com.getyourguide.app.reviews.models.Review

class ReviewsListAdapter : PagedListAdapter<Review, ReviewsViewHolder>(DiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ReviewsViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

}