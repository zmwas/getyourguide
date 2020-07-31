package com.getyourguide.app.dependencyinjection

import com.getyourguide.app.application.GetYourGuideApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        ActivityModule::class,
        DataModule::class,
        FragmentModule::class,
        ViewModelModule::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: GetYourGuideApplication): Builder

        fun build(): AppComponent
    }

    fun inject(app: GetYourGuideApplication)
}
