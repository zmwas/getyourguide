package com.getyourguide.app.dependencyinjection

import com.getyourguide.app.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity
}
