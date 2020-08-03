package com.getyourguide.app.reviews.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.getyourguide.app.reviews.data.ReviewsApiService
import com.getyourguide.app.reviews.models.Author
import com.getyourguide.app.reviews.models.Pagination
import com.getyourguide.app.reviews.models.Review
import com.getyourguide.app.reviews.models.ReviewsResponse
import com.getyourguide.app.reviews.viewmodels.utils.RxSchedulersOverrideRule
import com.getyourguide.app.reviews.viewmodels.utils.asPagedList
import com.getyourguide.app.reviews.viewmodels.utils.getOrAwaitValue
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ReviewsViewModelTest {

    @Mock
    private lateinit var apiService: ReviewsApiService

    private lateinit var viewModel: ReviewsViewModel

    private lateinit var reviewsResponse: ReviewsResponse

    private lateinit var reviewsList: ArrayList<Review>

    @get:Rule
    val schedulersOverrideRule =
        RxSchedulersOverrideRule()

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = ReviewsViewModel(apiService)
        val review = buildReview(1, "very fun", "I loved it", true, 4)
        reviewsList = ArrayList()
        reviewsList.add(review)
        reviewsResponse = ReviewsResponse(reviewsList, 1, 4.0, Pagination(10, 0))

    }


    @Test
    fun `fetching of reviews is successful`() {
        Mockito.`when`(apiService.fetchReviews(anyInt(), anyInt())).thenReturn(
            Single.just(
                reviewsResponse
            )
        )
        val liveData = viewModel.fetchReviewsForActivity()
        val reviews = liveData.getOrAwaitValue { }.asPagedList()
        val review = reviews!![0]
        Assert.assertEquals(1, reviews.size)
        Assert.assertEquals("very fun", review!!.title)
        Assert.assertEquals(true, review.isAnonymous)
        Assert.assertEquals(4, review.rating)
    }

    @Test
    fun `refreshing clears livedata and fetches new results`() {
        Mockito.`when`(apiService.fetchReviews(anyInt(), anyInt())).thenReturn(
            Single.just(
                reviewsResponse
            )
        )
        var liveData = viewModel.fetchReviewsForActivity()
        val reviews = liveData.getOrAwaitValue { }.asPagedList()
        val review = reviews!![0]
        Assert.assertEquals(1, reviews.size)
        Assert.assertEquals("very fun", review!!.title)

        viewModel.refresh()
        fetchNewResponse()

        liveData = viewModel.fetchReviewsForActivity()
        val newReviews = liveData.getOrAwaitValue { }.asPagedList()
        val newReview = newReviews!![1]
        Assert.assertEquals(2, newReviews.size)
        Assert.assertEquals("best experience ever", newReview!!.title)
    }

    private fun fetchNewResponse() {
        reviewsList.add(buildReview(2, "best experience ever", "Would do it again", false, 5))
        reviewsResponse = ReviewsResponse(reviewsList, 2, 4.0, Pagination(10, 0))
        Mockito.`when`(apiService.fetchReviews(anyInt(), anyInt())).thenReturn(
            Single.just(
                reviewsResponse
            )
        )

    }

    private fun buildReview(
        id: Int,
        title: String,
        message: String,
        isAnonymous: Boolean,
        rating: Int
    ): Review {
        return Review(
            id, buildAuthor(),
            title, message,
            "Tour guide", isAnonymous, rating, "2020-02-29T08:36:10+01:00",
            "English",
            ""
        )
    }

    private fun buildAuthor(): Author {
        return Author("Gemini Cricket", "Italy", "")
    }
}