package com.getyourguide.app.reviews.data

import com.getyourguide.app.reviews.data.ReviewsApiService
import com.getyourguide.app.reviews.models.ReviewsResponse
import io.reactivex.Single
import javax.inject.Inject

class ReviewsRepository @Inject constructor(private val reviewsApiService: ReviewsApiService) {
    fun fetchReviews(limit: Int, offset: Int): Single<ReviewsResponse> {
        return reviewsApiService.fetchReviews(limit, offset)
    }
}