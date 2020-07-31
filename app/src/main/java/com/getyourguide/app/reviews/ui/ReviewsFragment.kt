package com.getyourguide.app.reviews.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.getyourguide.app.R
import com.getyourguide.app.databinding.FragmentReviewsListBinding
import com.getyourguide.app.reviews.viewmodels.ReviewsViewModel
import com.getyourguide.app.utils.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class ReviewsFragment : Fragment() {
    @Inject
    lateinit var factory: ViewModelFactory
    private lateinit var binding: FragmentReviewsListBinding
    private lateinit var adapter: ReviewsListAdapter
    private lateinit var viewModel: ReviewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        viewModel = ViewModelProvider(this, factory).get(ReviewsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_reviews_list, container, false)
        setUpReviewsList()
        return binding.root
    }

    private fun setUpReviewsList() {
        adapter = ReviewsListAdapter()
        binding.reviewList.adapter = adapter
        viewModel.fetchReviewsForActivity()
            .observe(viewLifecycleOwner, Observer(adapter::submitList))
    }
}
