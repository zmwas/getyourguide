package com.getyourguide.app.reviews.data

import com.getyourguide.app.reviews.models.ReviewsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ReviewsApiService {
    @GET("/activities/23776/reviews")
    fun fetchReviews(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Single<ReviewsResponse>
}
