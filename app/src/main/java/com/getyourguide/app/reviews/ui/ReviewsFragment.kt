package com.getyourguide.app.reviews.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.getyourguide.app.R
import com.getyourguide.app.databinding.FragmentReviewsListBinding
import com.getyourguide.app.reviews.models.Review
import com.getyourguide.app.reviews.viewmodels.ReviewsViewModel
import com.getyourguide.app.utils.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class ReviewsFragment : Fragment(), ItemClickedCallback {
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
        setUpRefreshListener()
        setUpReviewsList()
        setNetworkState()
        return binding.root
    }

    private fun setNetworkState() =
        viewModel.getNetworkState().observe(viewLifecycleOwner, Observer(adapter::setNetwotkState))

    private fun setUpRefreshListener() = binding.refreshLayout.setOnRefreshListener {
        viewModel.refresh()
    }

    private fun setUpReviewsList() {
        adapter = ReviewsListAdapter(this)
        binding.reviewList.adapter = adapter
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = VERTICAL
        binding.reviewList.layoutManager = layoutManager
        viewModel.fetchReviewsForActivity()
            .observe(viewLifecycleOwner, Observer(this::displayReviews))
    }

    private fun displayReviews(reviews: PagedList<Review>) {
        binding.loading.visibility = GONE
        binding.reviewList.visibility = VISIBLE
        adapter.submitList(reviews)
        binding.refreshLayout.isRefreshing = false
    }

    override fun onItemClicked(review: Review) {
        val fragment = ReviewDetailFragment()
        val args = Bundle()
        args.putSerializable("review", review)
        fragment.arguments = args
        val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.content, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
