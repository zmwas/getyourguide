package com.getyourguide.app.reviews.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.getyourguide.app.reviews.data.ReviewsDataSource
import com.getyourguide.app.reviews.data.ReviewsRepository
import com.getyourguide.app.reviews.models.Reviews

class ReviewsDataSourceFactory (private val reviewsRepository: ReviewsRepository) :
    DataSource.Factory<Int, Reviews>() {
    private val sourceLiveData = MutableLiveData<ReviewsDataSource>()
    private lateinit var latestSource: ReviewsDataSource

    override fun create(): DataSource<Int, Reviews> {
        latestSource =
            ReviewsDataSource(reviewsRepository)
        sourceLiveData.postValue(latestSource)
        return latestSource
    }
}