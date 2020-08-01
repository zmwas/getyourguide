package com.getyourguide.app.reviews.data

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.paging.PositionalDataSource
import com.getyourguide.app.reviews.models.Review
import com.getyourguide.app.utils.NetworkState

class ReviewsDataSource(private val apiService: ReviewsApiService) :
    PositionalDataSource<Review>() {
    val networkState = MutableLiveData<NetworkState>()

    @SuppressLint("CheckResult")
    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Review>) {
        networkState.postValue(NetworkState.LOADING)
        apiService.fetchReviews(params.loadSize, params.startPosition).subscribe({
            networkState.postValue(NetworkState.LOADED)
            callback.onResult(it.reviews)
        }, this::postError)
    }

    @SuppressLint("CheckResult")
    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Review>) {
        networkState.postValue(NetworkState.LOADING)
        apiService.fetchReviews(params.requestedLoadSize, 0).subscribe(
            {
                networkState.postValue(NetworkState.LOADED)
                callback.onResult(it.reviews, 0)
            }, this::postError
        )
    }


    private fun postError(throwable: Throwable) {
        networkState.postValue(
            NetworkState.error(throwable)
        )
    }
}
