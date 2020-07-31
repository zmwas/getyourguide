package com.getyourguide.app.reviews.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.getyourguide.app.R
import com.getyourguide.app.databinding.FragmentReviewsListBinding

class ReviewsFragment : Fragment() {

    private lateinit var binding: FragmentReviewsListBinding
    private lateinit var adapter: ReviewsListAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_reviews_list, container, false)
        adapter = ReviewsListAdapter()
        binding.reviewList.adapter = adapter

        return binding.root
    }

}