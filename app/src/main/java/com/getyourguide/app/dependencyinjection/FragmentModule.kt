package com.getyourguide.app.dependencyinjection

import com.getyourguide.app.reviews.ui.ReviewsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun reviewsFragment(): ReviewsFragment
}
