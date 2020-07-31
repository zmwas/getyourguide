package com.getyourguide.app.reviews.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Pagination (
	@SerializedName("limit") @Expose val limit : Int,
	@SerializedName("offset") @Expose val offset : Int
)