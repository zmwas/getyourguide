package com.getyourguide.app.reviews.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.Config
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.getyourguide.app.reviews.data.ReviewsApiService
import com.getyourguide.app.reviews.data.ReviewsDataSourceFactory
import com.getyourguide.app.reviews.models.Review
import com.getyourguide.app.utils.NetworkState
import javax.inject.Inject

class ReviewsViewModel @Inject constructor(apiService: ReviewsApiService) : ViewModel() {
    private var reviewsLiveData: LiveData<PagedList<Review>>
    private var  reviewsDataSource:ReviewsDataSourceFactory = ReviewsDataSourceFactory(apiService)
    private val networkState = Transformations.switchMap(reviewsDataSource.sourceLiveData) {
        it.networkState
    }

    init {
        val pagedListConfig = Config(pageSize = 10, enablePlaceholders = false)
        reviewsLiveData = reviewsDataSource.toLiveData(pagedListConfig)
    }

    fun fetchReviewsForActivity(): LiveData<PagedList<Review>> {

        return reviewsLiveData
    }

    fun refresh() {
        reviewsDataSource.sourceLiveData.value?.invalidate()
    }

    fun getNetworkState():LiveData<NetworkState> {
        return networkState
    }

}
