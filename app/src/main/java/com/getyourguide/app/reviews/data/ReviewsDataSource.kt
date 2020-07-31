package com.getyourguide.app.reviews.data

import android.annotation.SuppressLint
import androidx.paging.PositionalDataSource
import com.getyourguide.app.reviews.models.Reviews
import io.reactivex.functions.Consumer

class ReviewsDataSource(private val reviewsRepository: ReviewsRepository) :
    PositionalDataSource<Reviews>() {

    @SuppressLint("CheckResult")
    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Reviews>) {
        reviewsRepository.fetchReviews(params.loadSize, params.startPosition).subscribe(Consumer {
            callback.onResult(it.reviews)
        })
    }

    @SuppressLint("CheckResult")
    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Reviews>) {
        reviewsRepository.fetchReviews(0, 10).subscribe(
            Consumer {
                callback.onResult(it.reviews, 0)
            })
    }
}