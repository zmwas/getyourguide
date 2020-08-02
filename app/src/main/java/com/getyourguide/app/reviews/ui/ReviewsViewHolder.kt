package com.getyourguide.app.reviews.ui

import android.text.TextUtils
import androidx.recyclerview.widget.RecyclerView
import com.getyourguide.app.R
import com.getyourguide.app.databinding.ReviewItemLayoutBinding
import com.getyourguide.app.reviews.models.Review
import com.squareup.picasso.Picasso

class ReviewsViewHolder(private val binding: ReviewItemLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(review: Review?) {
        binding.review = review
        if (TextUtils.isEmpty(review?.author?.photo))
            binding.reviewerImage.setImageResource(R.drawable.image_placeholder)
        else Picasso.get().load(review?.author?.photo).into(binding.reviewerImage)

    }

    fun getBinding(): ReviewItemLayoutBinding {
        return binding
    }
}
