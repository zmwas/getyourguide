package com.getyourguide.app.reviews.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ReviewsResponse(
    @SerializedName("reviews") @Expose val reviews: List<Review>,
    @SerializedName("totalCount") @Expose val totalCount: Int,
    @SerializedName("averageRating") @Expose val averageRating: Double,
    @SerializedName("pagination") @Expose val pagination: Pagination
)