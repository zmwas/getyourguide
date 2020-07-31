package com.getyourguide.app.reviews.data

import android.annotation.SuppressLint
import androidx.paging.PositionalDataSource
import com.getyourguide.app.reviews.models.Review
import io.reactivex.functions.Consumer

class ReviewsDataSource(private val reviewsRepository: ReviewsRepository) :
    PositionalDataSource<Review>() {

    @SuppressLint("CheckResult")
    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Review>) {
        reviewsRepository.fetchReviews(params.loadSize, params.startPosition).subscribe(Consumer {
            callback.onResult(it.reviews)
        })
    }

    @SuppressLint("CheckResult")
    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Review>) {
        reviewsRepository.fetchReviews(0, params.requestedLoadSize).subscribe(
            Consumer {
                callback.onResult(it.reviews, 0)
            })
    }
}