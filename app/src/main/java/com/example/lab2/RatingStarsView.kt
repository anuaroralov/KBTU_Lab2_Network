package com.example.lab2

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout

class RatingStarsView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val maxRating = 5

    init {
        orientation = HORIZONTAL
        updateStars(0)
    }

    fun updateStars(rating: Int) {
        removeAllViews()
        for (i in 1..maxRating) {
            val star = ImageView(context).apply {
                layoutParams = LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT
                ).also { it.setMargins(4, 0, 4, 0) }
                setImageResource(if (i <= rating) R.drawable.filled_star else R.drawable.star)
            }
            addView(star)
        }
    }
}
