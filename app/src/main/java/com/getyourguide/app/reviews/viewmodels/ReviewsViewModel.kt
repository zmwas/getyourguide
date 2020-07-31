package com.getyourguide.app.reviews.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.Config
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.getyourguide.app.reviews.data.ReviewsApiService
import com.getyourguide.app.reviews.data.ReviewsDataSourceFactory
import com.getyourguide.app.reviews.models.Review
import javax.inject.Inject

class ReviewsViewModel @Inject constructor(apiService: ReviewsApiService) : ViewModel() {
    var reviewsLiveData: LiveData<PagedList<Review>>

    init {
        val reviewsDataSource = ReviewsDataSourceFactory(apiService)
        val pagedListConfig = Config(pageSize = 10, enablePlaceholders = true)
        reviewsLiveData = reviewsDataSource.toLiveData(pagedListConfig)
    }

    fun fetchReviewsForActivity(): LiveData<PagedList<Review>> {
        return reviewsLiveData
    }
}
