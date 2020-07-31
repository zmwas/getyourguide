package com.getyourguide.app.reviews.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Author(
    @SerializedName("fullName") @Expose val fullName: String,
    @SerializedName("country") @Expose val country: String,
    @SerializedName("photo") @Expose val photo: String
)