package com.getyourguide.app.reviews.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.getyourguide.app.R
import com.getyourguide.app.databinding.ItemLoadMoreBinding
import com.getyourguide.app.databinding.ReviewItemLayoutBinding
import com.getyourguide.app.reviews.models.Review
import com.getyourguide.app.utils.NetworkState
import com.getyourguide.app.utils.Status

class ReviewsListAdapter(private val itemClickedCallback: ItemClickedCallback) :
    PagedListAdapter<Review, RecyclerView.ViewHolder>(DiffUtil()) {
    private lateinit var networkState: NetworkState
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_LOADER -> {
                val binding: ItemLoadMoreBinding =
                    DataBindingUtil.inflate(inflater, R.layout.item_load_more, parent, false)
                LoadingViewHolder(binding)
            }
            TYPE_REVIEW -> {
                val binding: ReviewItemLayoutBinding = DataBindingUtil
                    .inflate(inflater, R.layout.review_item_layout, parent, false)
                ReviewsViewHolder(binding)
            }
            else -> throw RuntimeException("There is no type that matches the type $viewType + make sure your using types correctly")
        }
    }

    fun setNetworkState(networkState: NetworkState) {
        this.networkState = networkState
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ReviewsViewHolder -> {
                holder.bind(getItem(position))
                holder.getBinding().root.setOnClickListener {
                    itemClickedCallback.onItemClicked(currentList?.get(position)!!)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            networkState.status == Status.RUNNING && position == itemCount - 1 -> TYPE_LOADER
            else -> TYPE_REVIEW
        }
    }

    companion object {
        private const val TYPE_LOADER: Int = 0
        private const val TYPE_REVIEW: Int = 1
    }
}
