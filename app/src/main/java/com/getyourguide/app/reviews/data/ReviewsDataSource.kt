package com.getyourguide.app.reviews.data

import android.annotation.SuppressLint
import androidx.paging.PositionalDataSource
import com.getyourguide.app.reviews.models.Review
import io.reactivex.functions.Consumer

class ReviewsDataSource(private val apiService: ReviewsApiService) :
    PositionalDataSource<Review>() {

    @SuppressLint("CheckResult")
    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Review>) {
        apiService.fetchReviews(params.loadSize, params.startPosition).subscribe(Consumer {
            callback.onResult(it.reviews)
        })
    }

    @SuppressLint("CheckResult")
    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Review>) {
        apiService.fetchReviews(0, params.requestedLoadSize).subscribe(
            Consumer {
                callback.onResult(it.reviews, 0)
            })
    }
}