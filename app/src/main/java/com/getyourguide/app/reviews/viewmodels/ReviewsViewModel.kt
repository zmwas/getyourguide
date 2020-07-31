package com.getyourguide.app.reviews.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.Config
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.getyourguide.app.reviews.data.ReviewsDataSourceFactory
import com.getyourguide.app.reviews.data.ReviewsRepository
import com.getyourguide.app.reviews.models.Review
import javax.inject.Inject

class ReviewsViewModel @Inject constructor(private val reviewsRepository: ReviewsRepository) :
    ViewModel() {
    fun fetchReviewsForActivity(): LiveData<PagedList<Review>> {
        val reviewsDataSource =
            ReviewsDataSourceFactory(
                reviewsRepository
            )
        val pagedListConfig = Config(pageSize = 10, enablePlaceholders = true)
        return reviewsDataSource.toLiveData(pagedListConfig)
    }
}