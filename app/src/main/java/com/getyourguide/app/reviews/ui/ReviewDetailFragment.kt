package com.getyourguide.app.reviews.ui

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.getyourguide.app.R
import com.getyourguide.app.databinding.FragmentReviewDetailBinding
import com.getyourguide.app.reviews.models.Review
import com.squareup.picasso.Picasso
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat

class ReviewDetailFragment : Fragment() {
    private lateinit var review: Review
    private lateinit var binding: FragmentReviewDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            review = (arguments!!.getSerializable("review") as Review?)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_review_detail, container, false)
        binding.review = review
        setDateText(review.created)
        loadImage(review?.author?.photo)
        return binding.root
    }

    private fun setDateText(dateString: String) {
        try {
            val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX").parse(dateString)
            val dateFormat: DateFormat = SimpleDateFormat("EEEE, MMMM dd yyyy")
            binding.dateCreated.text = dateFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }

    private fun loadImage(imageURL: String) {
        if (TextUtils.isEmpty(imageURL))
            binding.reviewerImage.setImageResource(R.drawable.image_placeholder)
        else Picasso.get().load(imageURL).into(binding.reviewerImage)
    }
}