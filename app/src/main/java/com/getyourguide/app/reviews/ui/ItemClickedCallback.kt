package com.getyourguide.app.reviews.ui

import com.getyourguide.app.reviews.models.Review

interface ItemClickedCallback {
    fun onItemClicked(review:Review)
}