package com.anubhav.ratingbarplayground.customratingbar

import android.view.View
import com.anubhav.ratingbarplayground.R
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.children

class CustomRatingBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var selectedDrawable: Drawable
    private var unselectedDrawable: Drawable
    private var numberOfItems: Int = 0

    init {
        orientation = HORIZONTAL

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CustomRatingBar,
            defStyleAttr,
            0
        ).apply {
            try {
                selectedDrawable = getDrawable(R.styleable.CustomRatingBar_selectedDrawable)!!
                unselectedDrawable = getDrawable(R.styleable.CustomRatingBar_unselectedDrawable)!!
                numberOfItems = getInt(R.styleable.CustomRatingBar_numberOfItems, 5)
            } finally {
                recycle()
            }
        }

        setupRatingItems()
    }

    private fun setupRatingItems() {
        for (i in 0 until numberOfItems) {
            val imageView = createRatingItemImageView()
            imageView.setImageDrawable(unselectedDrawable)
            imageView.tag = i
            imageView.setOnClickListener { onRatingItemClick(it) }
            addView(imageView)
        }
    }

    private fun createRatingItemImageView() = ImageView(context).apply {
        layoutParams = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )
    }

    private fun onRatingItemClick(view: View) {
        val position = view.tag as Int
        setRating(position + 1)
    }

    fun setRating(rating: Int) {
        for ((index, child) in children.withIndex()) {
            val drawable = if (index < rating) selectedDrawable else unselectedDrawable
            (child as ImageView).setImageDrawable(drawable)
        }
    }
}
