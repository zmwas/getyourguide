package com.getyourguide.app.reviews.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.getyourguide.app.reviews.models.Review

class ReviewsDataSourceFactory (private val reviewsRepository: ReviewsRepository) :
    DataSource.Factory<Int, Review>() {
    private val sourceLiveData = MutableLiveData<ReviewsDataSource>()
    private lateinit var latestSource: ReviewsDataSource

    override fun create(): DataSource<Int, Review> {
        latestSource =
            ReviewsDataSource(reviewsRepository)
        sourceLiveData.postValue(latestSource)
        return latestSource
    }
}