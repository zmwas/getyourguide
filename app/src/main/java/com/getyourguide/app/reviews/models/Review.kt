package com.getyourguide.app.reviews.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Review(
    @SerializedName("id") @Expose val id: Int,
    @SerializedName("author") @Expose val author: Author,
    @SerializedName("title") @Expose val title: String,
    @SerializedName("message") @Expose val message: String,
    @SerializedName("enjoyment") @Expose val enjoyment: String,
    @SerializedName("isAnonymous") @Expose val isAnonymous: Boolean,
    @SerializedName("rating") @Expose val rating: Int,
    @SerializedName("created") @Expose val created: String,
    @SerializedName("language") @Expose val language: String,
    @SerializedName("travelerType") @Expose val travelerType: String
)