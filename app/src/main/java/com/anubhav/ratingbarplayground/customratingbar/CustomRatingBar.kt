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
    private var itemSpacing: Int = 0
    private var itemWidth: Int = 0
    private var itemHeight: Int = 0

    private var onRatingChangedListener: OnRatingChangedListener? = null

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
                itemSpacing = getDimensionPixelSize(R.styleable.CustomRatingBar_ratingItemSpacing, 0)
                itemWidth = getDimensionPixelSize(R.styleable.CustomRatingBar_ratingItemWidth, LayoutParams.WRAP_CONTENT)
                itemHeight = getDimensionPixelSize(R.styleable.CustomRatingBar_ratingItemHeight, LayoutParams.WRAP_CONTENT)
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
            itemWidth,
            itemHeight
        ).apply {
            marginEnd = itemSpacing
        }
    }

    private fun onRatingItemClick(view: View) {
        val position = view.tag as Int
        val rating = position + 1
        setRating(rating)
        onRatingChangedListener?.onRatingChanged(rating)
    }

    fun setRating(rating: Int) {
        for ((index, child) in children.withIndex()) {
            val drawable = if (index < rating) selectedDrawable else unselectedDrawable
            (child as ImageView).setImageDrawable(drawable)
        }
    }

    fun setOnRatingChangedListener(listener: OnRatingChangedListener?) {
        onRatingChangedListener = listener
    }

    interface OnRatingChangedListener {
        fun onRatingChanged(rating: Int)
    }

}

