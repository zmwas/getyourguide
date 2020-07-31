package com.getyourguide.app.dependencyinjection

import com.getyourguide.app.BuildConfig.BASE_URL
import com.getyourguide.app.BuildConfig.USER_AGENT
import com.getyourguide.app.reviews.data.ReviewsApiService
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient().newBuilder()
            .addInterceptor(loggingInterceptor)
            .addNetworkInterceptor(userAgentInterceptor())
            .build()
    }

    private fun userAgentInterceptor(): Interceptor {
        return Interceptor {
            val requestBuilder: Request.Builder = it.request().newBuilder()
                .header("User-Agent", USER_AGENT)
            it.proceed(requestBuilder.build())
        }
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideReviewsApiService(retrofit: Retrofit): ReviewsApiService {
        return retrofit.create(ReviewsApiService::class.java)
    }

}
