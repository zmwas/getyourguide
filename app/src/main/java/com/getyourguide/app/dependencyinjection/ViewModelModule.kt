package com.getyourguide.app.dependencyinjection

import androidx.lifecycle.ViewModel
import com.getyourguide.app.reviews.viewmodels.ReviewsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ReviewsViewModel::class)
    abstract fun bindFetchReviewsViewModel(viewModel: ReviewsViewModel): ViewModel
}
