package com.getyourguide.app.reviews.viewmodels

import com.getyourguide.app.reviews.data.ReviewsApiService
import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ReviewsViewModelTest {

    @Mock
    private lateinit var apiService:ReviewsApiService

    private lateinit var viewModel: ReviewsViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = ReviewsViewModel(apiService)
    }



}